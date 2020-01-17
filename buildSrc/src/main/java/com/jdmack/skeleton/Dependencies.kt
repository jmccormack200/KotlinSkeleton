package com.jdmack.skeleton

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object Libs {

    val kotlinVersion = "1.3.50"
    val navigationVersion = "2.2.0-rc04"
    val coroutinesVersion = "1.3.0-RC2"
    val roomVersion = "2.1.0"
    val kotlinCore = "1.0.2"
    val archLifecycleVersion = "2.2.0-rc03"
    val retrofitVersion = "2.6.1"
    val daggerVersion = "2.13"

    object SharedConfig {
        val compileSDK = 29
        val targetSDK = 29
        val minSDK = 24
    }

    object Plugins {
        val buildGradlePlugin = "com.android.tools.build:gradle:3.5.0"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41"
        val artifactoryPlugin = "org.jfrog.buildinfo:build-info-extractor-gradle:4.9.6"
        val gmsPlugin = "com.google.gms:google-services:4.3.0"
        val navArgPlugin =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
        val dokkaPlugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.18"
    }

    object Kotlin {
        val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        val core = "androidx.core:core-ktx:$kotlinCore"
    }

    object Coroutine {
        val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Lifecycle {
        val extension = "androidx.lifecycle:lifecycle-extensions:$archLifecycleVersion"
        val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$archLifecycleVersion"
        val liveData = "androidx.lifecycle:lifecycle-livedata:$archLifecycleVersion"
        val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$archLifecycleVersion"
    }

    object Navigation {
        val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }

    object Room {
        val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        val roomKtx = "androidx.room:room-ktx:$roomVersion"
        val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }

    object Network {
        val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        val retrofitCoroutineAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.0.1"
        val okhttpMockWebServer = "com.squareup.okhttp3:mockwebserver:4.2.1"
    }

    object Core {
        val appCompat = "androidx.appcompat:appcompat:1.1.0-rc01"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta2"
        val annotation = "androidx.annotation:annotation:1.1.0"
        val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-beta01"
        val timber = "com.jakewharton.timber:timber:4.7.1"
        val fragment = "androidx.fragment:fragment-ktx:1.1.0"
    }

    object Misc {
        val material = "com.google.android.material:material:1.1.0-alpha09"
        val gson = "com.google.code.gson:gson:2.8.5"
        val cardView = "androidx.cardview:cardview:1.0.0"
        val threeTenABP = "com.jakewharton.threetenabp:threetenabp:1.2.1"
        val instaBug = "com.instabug.library:instabug:9.0.2"
        val preferences = "androidx.preference:preference-ktx:1.1.0"
        val picasso = "com.squareup.picasso:picasso:2.71828"
        val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.0"
    }

    object Dagger {
        val dagger = "com.google.dagger:dagger:$daggerVersion"
        val daggerAnnotations = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object SharedTestLibs {
        val junit = "junit:junit:4.12"
        val junitKtx = "androidx.test.ext:junit-ktx:1.1.1"
        val coreTesting = "androidx.arch.core:core-testing:2.0.1"
        val espresso = "androidx.test.espresso:espresso-core:3.2.0"
        val roomTesting = "androidx.room:room-testing:$roomVersion"
        val mockk = "io.mockk:mockk:1.9.3"
        val testCoreKtx = "androidx.test:core-ktx:1.2.0"
        val testRule = "androidx.test:rules:1.2.0"
        val fragmentTest = "androidx.fragment:fragment-testing:1.2.0-alpha01"
        val testCore = "androidx.test:core:1.2.0"
    }

    object UnitTestLib {
        val robolectric = "org.robolectric:robolectric:4.3"
        val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
    }
}

fun Project.baseDependencies() {
    dependencies {
        "implementation"(fileTree("libs") { include("*.jar") })

        "implementation"(Libs.Kotlin.jvm)
        "implementation"(Libs.Core.timber)
        "implementation"(Libs.Kotlin.core)
        "testImplementation"(Libs.SharedTestLibs.junit)
        "testImplementation"(Libs.SharedTestLibs.junitKtx)
        "testImplementation"(Libs.SharedTestLibs.coreTesting)
        "testImplementation"(Libs.SharedTestLibs.mockk)
    }
}

fun Project.baseAndroidDependencies() {
    dependencies {
            "implementation"(Libs.Lifecycle.extension)
            "implementation"(Libs.Lifecycle.viewModel)
            "implementation"(Libs.Lifecycle.liveData)
            "kapt"(Libs.Lifecycle.lifecycleCompiler)

            "implementation"(Libs.Navigation.fragment)
            "implementation"(Libs.Navigation.navigationUI)

            "implementation"(Libs.Core.appCompat)
            "implementation"(Libs.Core.constraintLayout)
            "implementation"(Libs.Core.annotation)
            "implementation"(Libs.Core.recyclerView)
            "implementation"(Libs.Core.fragment)

            "testImplementation"(Libs.UnitTestLib.robolectric)
            "testImplementation"(Libs.SharedTestLibs.espresso)

            "debugImplementation"(Libs.Misc.leakCanary)
    }
}

