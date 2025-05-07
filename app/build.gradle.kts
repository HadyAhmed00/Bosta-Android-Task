plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)

    alias(libs.plugins.hilt)
}

android {
    namespace = "com.hady.citiesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hady.citiesapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://stg-app.bosta.co/api/v2/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://stg-app.bosta.co/api/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Timber
    implementation(libs.timber)
    // Http Interceptor
    implementation(libs.logging.interceptor)

    // Hilt dependencies
    implementation(libs.hilt.android)  // Hilt Android dependency
    kapt(libs.hilt.compiler)


    // Coroutines for Android (for using on the main thread)
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel and LiveData (if you're using these with coroutines)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
}