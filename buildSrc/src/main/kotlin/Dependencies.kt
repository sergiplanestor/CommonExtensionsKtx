const val kotlinVersion = "1.5.31"

object Pluggins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object AndroidSdk {
    const val min = 24
    const val compile = 31
    const val target = compile
    const val buildToolsVersion = "29.0.2"
    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Version {
    private const val major = 1
    private const val minor = 0
    private const val patch = 0
    const val code = 1
    const val name = "$major.$minor.$patch"
}

object Libs {
    private object Versions {
        //Android X
        const val appCompat = "1.4.0"
        //AndroidX
        const val ktx = "1.7.0"
    }

    //Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    //AndroidX
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    //AndroidX Ktx
    const val androidxCore = "androidx.core:core-ktx:${Versions.ktx}"
}