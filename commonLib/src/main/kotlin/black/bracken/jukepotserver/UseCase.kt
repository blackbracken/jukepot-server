package black.bracken.jukepotserver

/**
 * Represents Usecase.
 *
 * @param I InputTransferObject
 * @param O OutputTransferObject
 */
interface UseCase<in I, out O> {

    operator fun invoke(inputTransferObject: I): O

}