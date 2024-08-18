plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.petPulse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.petPulse"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
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
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom)) // Ensure Compose versions are consistent
    implementation(libs.ui) // Basic UI components for Compose
    implementation(libs.ui.tooling.preview) // Preview tools
    implementation(libs.compose.material) // Material design components for Compose
    implementation(libs.compose.foundation) // Foundation library for Compose
    implementation(libs.activity.compose) // Compose integration with Activities
    implementation(libs.core.splashscreen) // For splash screen handling
    implementation("androidx.wear.compose:compose-material:1.3.1") // Wear OS Compose Material components
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
