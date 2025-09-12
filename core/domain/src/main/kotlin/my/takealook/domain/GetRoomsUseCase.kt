package my.takealook.domain

import my.takealook.data.chat.ChatRepository
import my.takealook.domain.model.asDomainEntity
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke() = chatRepository
        .getRooms()
        .map { it.map { it.asDomainEntity() } }
}
