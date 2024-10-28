package al.bruno.event.greet.message.api.usecase

interface UpdateOrderUseCase {
    suspend operator fun invoke(id: Long)
}