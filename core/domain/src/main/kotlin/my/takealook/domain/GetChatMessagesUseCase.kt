package my.takealook.domain

import my.takealook.data.chat.ChatRepository
import my.takealook.domain.model.ChatUser
import my.takealook.domain.model.asDomainEntity
import javax.inject.Inject

class GetChatMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(roomId: Long) = chatRepository
        .getChatMessages(roomId)
        .map { it.map { it.asDomainEntity(ChatUser()) } } // TODO : Chat Uesr 조회 로직 필요
}
