package dependencies

@Suppress("unused", "MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object Dep {

    object GradlePlugin : DependencyCategory {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val shadow = "com.github.jengelman.gradle.plugins:shadow:5.2.0"

        override val implementedSet = setOf(shadow)
    }

    object Kotlin : DependencyCategory {
        const val version = "1.3.60"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"

        override val implementedSet = setOf(stdlib)
    }

    object KotlinX : DependencyCategory {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0"

        override val implementedSet = setOf<String>()
    }

    object Ktor : DependencyCategory {
        const val version = "1.3.1"

        const val serverCore = "io.ktor:ktor-server-core:$version"
        const val serverNetty = "io.ktor:ktor-server-netty:$version"
        const val serverHostCommon = "io.ktor:ktor-server-host-common:$version"
        const val auth = "io.ktor:ktor-auth:$version"

        const val serialization = "io.ktor:ktor-serialization:$version"

        const val serverTests = "io.ktor:ktor-server-tests:$version"

        const val logbackClassic = "ch.qos.logback:logback-classic:1.2.1"

        override val implementedSet = setOf(
            serverCore,
            serverNetty,
            serverHostCommon,
            auth,
            serialization,
            logbackClassic
        )
    }

    object Exposed : DependencyCategory {
        const val version = "0.17.7"

        const val core = "org.jetbrains.exposed:exposed:$version"

        const val mariadbJavaClient = "org.mariadb.jdbc:mariadb-java-client:2.4.4"

        override val implementedSet = setOf(core, mariadbJavaClient)
    }

    object Koin : DependencyCategory {
        const val version = "2.0.1"

        const val core = "org.koin:koin-core:$version"
        const val coreExt = "org.koin:koin-core-ext:$version"
        const val ktor = "org.koin:koin-ktor:$version"

        const val test = "org.koin:koin-test:$version"

        override val implementedSet = setOf(core, coreExt, ktor)
    }

    object Arrow : DependencyCategory {
        const val version = "0.10.4"

        const val core = "io.arrow-kt:arrow-core:$version"
        const val syntax = "io.arrow-kt:arrow-syntax:$version"

        const val meta = "io.arrow-kt:arrow-meta:$version"

        override val implementedSet = setOf(core, syntax)
    }

    object CommonsValidator : DependencyCategory {
        const val version = "1.6"

        const val core = "commons-validator:commons-validator:$version"

        override val implementedSet = setOf(core)
    }

    interface DependencyCategory {
        val implementedSet: Set<String>
    }

}