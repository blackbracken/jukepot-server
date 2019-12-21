package black.bracken.jukepotserver.core

import black.bracken.jukepotserver.port.UserController
import black.bracken.jukepotserver.service.user.register.UserRegisterInteractor
import black.bracken.jukepotserver.service.user.register.UserRegisterUsecase
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule = module(createdAtStart = true) {
    // TODO: abstract
    // Controller
    single<UserController>()

    // Usecase
    singleBy<UserRegisterUsecase, UserRegisterInteractor>()
}