package black.bracken.jukepotserver

import black.bracken.jukepotserver.adapter.controller.UserController
import black.bracken.jukepotserver.adapter.gateway.MariaUserRepository
import black.bracken.jukepotserver.adapter.gateway.UserRepository
import black.bracken.jukepotserver.service.user.register.UserRegisterInteractor
import black.bracken.jukepotserver.service.user.register.UserRegisterUsecase
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

internal val appModule = module(createdAtStart = true) {
    // Presentations
    single<UserController>()

    // Features
    singleBy<UserRegisterUsecase, UserRegisterInteractor>()

    // Infrastructure
    singleBy<UserRepository, MariaUserRepository>()
}