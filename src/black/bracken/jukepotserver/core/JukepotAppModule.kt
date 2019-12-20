package black.bracken.jukepotserver.core

import black.bracken.jukepotserver.port.UserController
import org.koin.dsl.module
import org.koin.experimental.builder.single

val appModule = module(createdAtStart = true) {
    single<UserController>()
}