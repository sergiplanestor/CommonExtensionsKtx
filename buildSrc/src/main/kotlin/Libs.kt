/**
 * Library's versions
 */
private object LibVersion {
    //Android X
    const val appCompat = "1.4.0"
    //AndroidX
    const val ktx = "1.7.0"
    // Material
    const val material = "1.4.0"
}

/**
 * Library's dependencies
 */
object Lib {
    //AndroidX
    const val appCompat = "androidx.appcompat:appcompat:${LibVersion.appCompat}"
    //AndroidX Ktx
    const val androidxCore = "androidx.core:core-ktx:${LibVersion.ktx}"
    // Material
    const val materialDesign = "com.google.android.material:material:${LibVersion.material}"
}