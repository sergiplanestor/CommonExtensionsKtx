plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinParcelize)
    id(Plugins.mavenPublish)
    id("org.jetbrains.dokka") version "0.9.17"
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

    implementation(Libs.appCompat)
    implementation(Libs.androidxCore)
    implementation(Libs.materialDesign)

    /*
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    */
}

tasks.dokka {
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
}

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

artifacts {
    archives(sourcesJar)
    archives(dokkaJar)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("debug") { applyConfig(isDebug = true) }
            create<MavenPublication>("release") { applyConfig(isDebug = false) }
        }
    }
}

fun MavenPublication.applyConfig(isDebug: Boolean) {
    from(components[if (isDebug) "debug_aab" else "release_aab"])
    artifact(sourcesJar)
    artifact(dokkaJar)

    groupId = Artifact.group
    artifactId = if (isDebug) Artifact.idDebug else Artifact.id
    version = Artifact.version
}