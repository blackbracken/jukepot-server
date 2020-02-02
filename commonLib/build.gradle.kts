import dependencies.Dep

dependencies {
    Dep.GradlePlugin.implementedSet.forEach { implementation(it) }
    Dep.Kotlin.implementedSet.forEach { implementation(it) }
}