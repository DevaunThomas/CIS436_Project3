plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cis436_project3"
    compileSdk {
        version = release(36)
    }

    // Enable view binding
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.cis436_project3"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.fragment:fragment-ktx:1.8.6")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Volley (latest stable as of 2026)
    implementation("com.android.volley:volley:1.2.1")
}