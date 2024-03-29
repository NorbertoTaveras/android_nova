import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.norbertotaveras.android_nova"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.norbertotaveras.android_news"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions.add("environment")
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { localProperties.load(it) }
    }
    productFlavors {
        //val apiKey = properties["apiKey"]?.toString() ?: "defaultApiKey"
        //val weatherApiKey = properties["weatherApiKey"]?.toString() ?: "defaultApiKey"
        val apiKey = localProperties.getProperty("apiKey", "defaultApiKey")
        val weatherApiKey = localProperties.getProperty("weatherApiKey", "defaultApiKey")
        create("stage") {
            dimension = "environment"
            applicationIdSuffix = ".stage"
            versionNameSuffix = "-stage"
            buildConfigField("String", "API_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"${apiKey}\"")
            buildConfigField("String", "WEATHER_API_URL", "\"http://api.weatherapi.com/v1/\"")
            buildConfigField("String", "WEATHER_API_KEY", "\"${weatherApiKey}\"")
            buildConfigField("String", "SDK_VERSION_NAME", "\"test\"")
        }
        create("production") {
            dimension = "environment"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            buildConfigField("String", "API_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"${apiKey}\"")
            buildConfigField("String", "WEATHER_API_URL", "\"http://api.weatherapi.com/v1/\"")
            buildConfigField("String", "WEATHER_API_KEY", "\"${weatherApiKey}\"")
            buildConfigField("String", "SDK_VERSION_NAME", "\"test\"")
        }
    }
}

dependencies {
    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.android.material.icons.extended)
    implementation(libs.android.material.icons.extended.alt)
    implementation(libs.androidx.navigation.compose)

    // hilt
    implementation(libs.android.hilt)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    kapt(libs.android.hilt.compiler)
    implementation(libs.android.hilt.navigation.compose)

    // paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    kapt(libs.androidx.room.annotation.processor)

    // coil
    implementation(libs.coil.compose)

    // splash
    implementation(libs.androidx.splash)

    // datastore
    implementation(libs.androidx.datastore)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.okhtpp)
    implementation(libs.retrofit.interceptor)

    // accompanist
    implementation(libs.accompanist)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // foundation
    implementation(libs.androidx.compose.foundation)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}