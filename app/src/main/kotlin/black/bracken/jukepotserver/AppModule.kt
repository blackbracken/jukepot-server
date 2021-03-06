package black.bracken.jukepotserver

import black.bracken.jukepotserver.database.repositories.MariaTokenRepository
import black.bracken.jukepotserver.database.repositories.MariaUserRepository
import black.bracken.jukepotserver.entity.repository.TokenRepository
import black.bracken.jukepotserver.entity.repository.UserRepository
import black.bracken.jukepotserver.presentation.UserPresentation
import black.bracken.jukepotserver.usecase.RegisterUser
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

internal val appModule = module(createdAtStart = true) {
    // Presentations
    single<UserPresentation>()

    // Features
    single<RegisterUser>()

    // Infrastructure
    singleBy<UserRepository, MariaUserRepository>()
    singleBy<TokenRepository, MariaTokenRepository>()
}