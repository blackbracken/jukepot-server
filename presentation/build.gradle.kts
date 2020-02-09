import dependencies.Dep

dependencies {
    Dep.Kotlin.implementedSet.forEach { implementation(it) }
    Dep.GradlePlugin.implementedSet.forEach { implementation(it) }
    Dep.Arrow.implementedSet.forEach { implementation(it) }

    implementation(project(":commonLib"))
    implementation(project(":usecase"))
}