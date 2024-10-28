package al.bruno.event.greet.message.api.usecase

interface AddToFavouriteUseCase {
    suspend operator fun invoke(isFavourite: Boolean, id: Long)
}