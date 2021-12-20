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
    implementation(Lib.androidxAppCompat)
    implementation(Lib.androidxCore)
}

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(project.android.sourceSets.getByName("main").java.srcDirs)
}

artifacts { archives(sourcesJar) }

afterEvaluate {
    publishing {
        publications {
            listOf(
                "debug" to true,
                "release" to false
            ).forEach { (flavor, isDebug) -> create<MavenPublication>(flavor) { applyConfig(isDebug) } }
        }
    }
}

fun MavenPublication.applyConfig(isDebug: Boolean) {
    from(components[ArtifactPublicationConfig.componentName(isDebug)])
    artifact(sourcesJar)

    groupId = ArtifactPublicationConfig.group
    artifactId = ArtifactPublicationConfig.artifactId(project.name, isDebug)
    version = ArtifactPublicationConfig.version
}