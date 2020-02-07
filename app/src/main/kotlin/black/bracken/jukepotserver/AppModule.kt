package black.bracken.jukepotserver

import black.bracken.jukepotserver.adapter.controller.UserController
import black.bracken.jukepotserver.database.repositories.MariaUserRepository
import black.bracken.jukepotserver.entity.repository.UserRepository
import black.bracken.jukepotserver.usecase.RegisterUser
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

internal val appModule = module(createdAtStart = true) {
    // Presentations
    single<UserController>()

    // Features
    single<RegisterUser>()

    // Infrastructure
    singleBy<UserRepository, MariaUserRepository>()
}