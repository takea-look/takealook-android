package my.takealook.data.chat

import my.takealook.TklApi
import my.takealook.model.chat.ChatMessageResult
import my.takealook.model.chat.ChatRoomResult
import my.takealook.model.toErrorResponse
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val api: TklApi
) : ChatRepository {

    override suspend fun getChatMessages(roomId: Long): Result<List<ChatMessageResult>> {
        return runCatching {
            val response = api.getChatMessages(roomId)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw response.errorBody()?.toErrorResponse()!!
            }
        }
    }

    override suspend fun getRooms(): Result<List<ChatRoomResult>> {
        println("call rooms")
        return runCatching {
            val response = api.getChatRooms()
            println("response: $response")
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw response.errorBody()?.toErrorResponse()!!
            }
        }
    }
}
