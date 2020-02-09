import dependencies.Dep

dependencies {
    Dep.GradlePlugin.implementedSet.forEach { implementation(it) }
    Dep.Kotlin.implementedSet.forEach { implementation(it) }
    Dep.Ktor.implementedSet.forEach { implementation(it) }
    Dep.Koin.implementedSet.forEach { implementation(it) }
    Dep.Arrow.implementedSet.forEach { implementation(it) }

    implementation(project(":commonLib"))
    implementation(project(":entity"))
    implementation(project(":usecase"))
    implementation(project(":presentation"))
    implementation(project(":infrastructure:database"))
}