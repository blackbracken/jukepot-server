package black.bracken.jukepotserver.usecase.register

import black.bracken.jukepotserver.usecase.Usecase

// TODO: Output should Either<ErrorReport, RegisterOutput>
class UserRegisterUsecase : Usecase<RegisterInput, RegisterOutput> {

    override fun invoke(inputTransferObject: RegisterInput): RegisterOutput = TODO()

}