package black.bracken.jukepotserver.usecase

interface Usecase<in I, out O> {

    operator fun invoke(inputTransferObject: I): O

}