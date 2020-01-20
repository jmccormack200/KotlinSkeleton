import com.jdmack.skeleton.Libs
import com.jdmack.skeleton.baseAndroidDependencies
import com.jdmack.skeleton.baseDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Libs.SharedConfig.compileSDK)

    defaultConfig {
        minSdkVersion(Libs.SharedConfig.minSDK)
        targetSdkVersion(Libs.SharedConfig.targetSDK)
        applicationId = "com.jdmccormack.skeleton"
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
    implementation(project(":commonui"))
}
