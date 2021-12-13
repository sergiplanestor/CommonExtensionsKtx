plugins {
    id(Pluggins.androidApplication)
    id(Pluggins.kotlinAndroid)
    id(Pluggins.kotlinAndroidExtensions)
    id(Pluggins.mavenPublish)
}

android {
    compileSdk = AndroidSdk.compile
    buildToolsVersion = AndroidSdk.buildToolsVersion
    defaultConfig {
        applicationId = "com.revolhope.commonextensionsktx"
        minSdk = AndroidSdk.min
        compileSdk = AndroidSdk.compile
        targetSdk = AndroidSdk.target
        versionCode = Version.code
        versionName = Version.name
        testInstrumentationRunner = AndroidSdk.instrumentationRunner
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(Libs.appCompat)
    implementation(Libs.androidxCore)
    implementation(Libs.materialDesign)

    /*
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    */
}