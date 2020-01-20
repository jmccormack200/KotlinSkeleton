package com.jdmccormack.common

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Client for generating api's using Retrofit
 */
object RetrofitClient {

    private const val CONNECTION_TIMEOUT = 30L

    /**
     * Generates a new REST API from the api's [baseUrl] and a given [service] interface
     */
    fun <T> createRestApi(baseUrl: String, service: Class<T>): T {
        val httpClient = newOkHttpClient()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(service)
    }

    var logging = HttpLoggingInterceptor()

    private fun newOkHttpClient(): OkHttpClient {
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }
}
