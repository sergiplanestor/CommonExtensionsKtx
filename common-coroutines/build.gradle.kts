plugins {
    id(Plugin.androidLibrary)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinParcelize)
    id(Plugin.mavenPublish)
}

android {
    compileSdk = AndroidSdkConfig.compile
    defaultConfig {
        minSdk = AndroidSdkConfig.min
        targetSdk = AndroidSdkConfig.target
        testInstrumentationRunner = AndroidSdkConfig.instrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Project.CORE.project(this))
    
    implementation(Lib.androidxCore)
    implementation(Lib.androidxAppCompat)
    implementation(Lib.androidxLiveData)
    implementation(Lib.androidxViewModel)

    api(Lib.kotlinCoroutines)
}