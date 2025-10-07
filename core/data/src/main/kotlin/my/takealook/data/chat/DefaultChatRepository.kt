package my.takealook.data.chat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import my.takealook.TklApi
import my.takealook.datastore.TklDataStore
import my.takealook.model.chat.ChatMessageResult
import my.takealook.model.chat.ChatRoomResult
import my.takealook.model.toErrorResponse
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val api: TklApi,
    private val dataStore: TklDataStore
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

    override suspend fun getRooms(): Flow<List<ChatRoomResult>> {
        return dataStore.accessToken.mapLatest { accessToken ->
            val response = api.getChatRooms(accessToken = accessToken)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw response.errorBody()?.toErrorResponse()!!
            }
        }
    }
}
