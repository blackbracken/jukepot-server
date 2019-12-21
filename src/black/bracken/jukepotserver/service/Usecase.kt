package black.bracken.jukepotserver.service

/**
 * Represents Usecase.
 *
 * @param I InputTransferObject
 * @param O OutputTransferObject
 */
interface Usecase<in I, out O> {

    operator fun invoke(inputTransferObject: I): O

}