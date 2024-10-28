plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.android.secrets)
    alias(libs.plugins.google.android.ksp)
}

android {
    namespace = "al.bruno.exchanger.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    //
    implementation(libs.converter.gson)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit)
    implementation("com.google.code.gson:gson:2.11.0")

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler
    )
    testImplementation(libs.retrofit)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(libs.converter.kotlinx.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}