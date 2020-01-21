package com.jdmccormack.commonui

import android.app.Application
import android.content.Context
import timber.log.Timber

class BaseApplication : Application() {

    init {
        instance = this
    }

    /**
     * Static accessors for the [applicationContext]
     */
    companion object {
        private var instance: BaseApplication? = null
        val applicationContext: Context by lazy {
            instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
