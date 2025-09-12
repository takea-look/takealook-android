package my.takealook.data.chat

import my.takealook.model.chat.ChatMessageResult
import my.takealook.model.chat.ChatRoomResult

interface ChatRepository {

    suspend fun getRooms(): Result<List<ChatRoomResult>>

    suspend fun getChatMessages(roomId: Long): Result<List<ChatMessageResult>>
}
