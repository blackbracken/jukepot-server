package black.bracken.jukepotserver

/**
 * Represents UseCase.
 *
 * @param I InputTransferObject
 * @param O OutputTransferObject
 */
interface UseCase<in I, out O> {

    operator fun invoke(inputTransferObject: I): O

}