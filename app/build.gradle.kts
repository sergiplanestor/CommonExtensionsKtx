plugins {
    id(Plugin.androidApplication)
    id(Plugin.kotlinAndroid)
    id(Plugin.kotlinParcelize)
    id(Plugin.mavenPublish)
    id(Plugin.jetbrainsDokka)
}

android {
    compileSdk = AndroidSdkConfig.compile
    buildToolsVersion = AndroidSdkConfig.buildToolsVersion
    defaultConfig {
        applicationId = "com.revolhope.commonextensionsktx"
        minSdk = AndroidSdkConfig.min
        compileSdk = AndroidSdkConfig.compile
        targetSdk = AndroidSdkConfig.target
        versionCode = BuildVersion.code
        versionName = BuildVersion.name
        testInstrumentationRunner = AndroidSdkConfig.instrumentationRunner
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
        jvmTarget = "11"
    }
}

dependencies {

    implementation(Lib.appCompat)
    implementation(Lib.androidxCore)
    implementation(Lib.materialDesign)

    /*
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    */
}

/*tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
    moduleName = rootProject.name
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
    dependsOn(tasks.dokka)
}*/

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

artifacts {
    archives(sourcesJar)
    //archives(dokkaJar)
}

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
    //artifact(dokkaJar)

    groupId = ArtifactPublicationConfig.group
    artifactId = ArtifactPublicationConfig.artifactId(isDebug)
    version = ArtifactPublicationConfig.version
}