object ArtifactPublicationConfig {
    private const val componentNameDebug = "debug_aab"
    private const val componentNameRelease = "release_aab"
    const val group = "com.github.sergiplanestor"
    const val id = "common-ktx"
    const val idDebug = "$id-debug"
    const val version = BuildVersion.name

    fun artifactId(isDebug: Boolean): String =
        if (isDebug) idDebug else id

    fun componentName(isDebug: Boolean): String =
        if (isDebug) componentNameDebug else componentNameRelease
}

