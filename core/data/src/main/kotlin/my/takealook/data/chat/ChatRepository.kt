package my.takealook.data.chat

import kotlinx.coroutines.flow.Flow
import my.takealook.model.chat.ChatMessageResult
import my.takealook.model.chat.ChatRoomResult

interface ChatRepository {

    suspend fun getRooms(): Flow<List<ChatRoomResult>>

    suspend fun getChatMessages(roomId: Long): Result<List<ChatMessageResult>>
}
