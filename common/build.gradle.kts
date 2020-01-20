import com.jdmack.skeleton.Libs
import com.jdmack.skeleton.baseAndroidDependencies
import com.jdmack.skeleton.baseDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Libs.SharedConfig.compileSDK)

    defaultConfig {
        minSdkVersion(Libs.SharedConfig.minSDK)
        targetSdkVersion(Libs.SharedConfig.targetSDK)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

baseDependencies()
baseAndroidDependencies()
dependencies {
}
