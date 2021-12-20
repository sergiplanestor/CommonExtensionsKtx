/**
 * Library's versions
 */
private object LibVersion {
    //Android X
    const val androidxCore = "1.7.0"
    const val androidxAppCompat = "1.4.0"
    const val androidxLifecycle = "2.3.1"

    //Kotlin
    const val kotlinCoroutines = "1.5.1"
    // Material
    const val materialDesign = "1.4.0"
}

/**
 * Library's dependencies
 */
object Lib {

    // Android X
    const val androidxCore = "androidx.core:core-ktx:${LibVersion.androidxCore}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${LibVersion.androidxAppCompat}"
    const val androidxLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${LibVersion.androidxLifecycle}"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersion.androidxLifecycle}"

    // Kotlin - Coroutines
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibVersion.kotlinCoroutines}"

    // Material
    const val materialDesign = "com.google.android.material:material:${LibVersion.materialDesign}"
}