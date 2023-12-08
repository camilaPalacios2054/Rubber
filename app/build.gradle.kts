plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.rubber"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rubber"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kotlinOptions {
        jvmTarget = "1.8" // Puedes ajustar esto según tu versión de Java preferida
    }

    buildFeatures {
        viewBinding=true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //RECYCLERVIEW
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    //MAPS
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //CAMARA
    implementation ("androidx.camera:camera-core:1.4.0-alpha02")
    implementation ("androidx.camera:camera-camera2:1.4.0-alpha02")
    implementation ("androidx.camera:camera-lifecycle:1.4.0-alpha02")
    implementation ("androidx.camera:camera-video:1.4.0-alpha02")

    implementation ("androidx.camera:camera-view:1.4.0-alpha02")
    implementation ("androidx.camera:camera-extensions:1.4.0-alpha02")

    //FIREBASE Y FIRESTORE

    implementation ("com.google.firebase:firebase-firestore:24.9.1")
    implementation ("com.google.firebase:firebase-analytics:21.5.0")
    //noinspection BomWithoutPlatform
    implementation ("com.google.firebase:firebase-bom:32.6.0")
    implementation("com.android.support:multidex:1.0.3")

    //MQTT
    implementation ("androidx.work:work-runtime:2.7.1")
    //noinspection GradleCompatible
    implementation ("com.android.support:support-v4:28.0.0")
    //noinspection GradleCompatible
    implementation ("com.android.support:localbroadcastmanager:28.0.0")

    implementation ("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.1.1")

    implementation ("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")
    {
        exclude(group="com.android.support")
        exclude(module="appcompat-v7")
        exclude(module="support-v4")
    }
}