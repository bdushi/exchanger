package al.bruno.event.greet.message.api.usecase

interface DeleteMessageUseCase {
    suspend operator fun invoke(id: Long)
}