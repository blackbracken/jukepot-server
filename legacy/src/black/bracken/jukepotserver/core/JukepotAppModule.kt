package black.bracken.jukepotserver.core

import black.bracken.jukepotserver.adapter.controller.UserController
import black.bracken.jukepotserver.adapter.gateway.MariaUserRepository
import black.bracken.jukepotserver.adapter.gateway.UserRepository
import black.bracken.jukepotserver.service.user.register.UserRegisterInteractor
import black.bracken.jukepotserver.service.user.register.UserRegisterUsecase
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule = module(createdAtStart = true) {
    // Controller
    single<UserController>()

    // Usecase
    singleBy<UserRegisterUsecase, UserRegisterInteractor>()

    // Repository
    singleBy<UserRepository, MariaUserRepository>()
}