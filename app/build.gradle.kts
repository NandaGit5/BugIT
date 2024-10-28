plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mobily.bugitapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mobily.bugitapp"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.coil.compose)
    implementation (libs.androidx.hilt.navigation.compose)
    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    implementation (libs.moshi)

    implementation (libs.volley)

    implementation (libs.androidx.runtime.livedata)

    implementation (libs.androidx.hilt.navigation.compose.v100)

    // GoogleSheet API

    //implementation ("com.google.api-client:google-api-client-android:1.31.5")
   // implementation ("com.google.apis:google-api-services-sheets:v4-rev20220820-1.32.1")
  //  implementation ("com.google.auth:google-auth-library-oauth2-http:1.1.0")
    /*implementation (libs.google.api.client.android)
    implementation (libs.google.api.services.sheets)
    implementation (libs.play.services.auth)*/

    /*implementation("com.google.api-client:google-api-client-android:1.33.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20230412-1.32.1")
    implementation("com.google.android.gms:play-services-auth:19.2.0")
*/
    //Coroutines
    implementation (libs.kotlinx.coroutines.android)


    // Hilt dependencies
    implementation (libs.hilt.android)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
