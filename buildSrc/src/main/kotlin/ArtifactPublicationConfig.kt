object ArtifactPublicationConfig {
    private const val componentNameDebug = "debug"
    private const val componentNameRelease = "release"
    const val group = "com.github.sergiplanestor"
    private const val idSuffix = "-ktx"
    private const val idDebugSuffix = "$idSuffix-debug"
    const val version = BuildVersion.name

    fun artifactId(name: String, isDebug: Boolean): String =
        if (isDebug) "$name$idDebugSuffix" else "$name$idSuffix"

    fun componentName(isDebug: Boolean): String =
        if (isDebug) componentNameDebug else componentNameRelease
}

