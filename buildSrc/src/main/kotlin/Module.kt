import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

enum class Project(internal val path: String) {
    // Core
    CORE(":common-core"),

    // Clean arch
    DATA(":common-data"),
    DOMAIN(":common-domain"),
    UI(":common-ui"),

    // Data layer - Firebase
    DATA_FIREBASE_AUTH(":common-data-firebase-auth"),
    DATA_FIREBASE_DB(":common-data-firebase-db"),

    // Coroutines
    COROUTINES(":common-coroutines")

    ;
}

fun Project.project(handler: DependencyHandler): ProjectDependency =
    handler.project(path = path)