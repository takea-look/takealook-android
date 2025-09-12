package my.takealook

import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import my.takealook.model.chat.ChatMessageResult
import my.takealook.model.chat.ChatRoomResult
import my.takealook.model.login.LoginBody
import my.takealook.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TklApi {

    @GET("stickers")
    suspend fun getStickers(
        @Query("categoryId") categoryId: Int? = null
    ): List<StickerResult>

    @GET("sticker-categories")
    suspend fun getStickerCategories(): List<StickerCategoryResult>

    @POST("auth/signin")
    suspend fun signIn(
        @Body body: LoginBody,
    ): Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signUp(
        @Body body: LoginBody,
    ): Response<Unit>

    @GET("chat/rooms")
    suspend fun getChatRooms(
        /** TODO: accessToken 헤더로 넣어주는 기능 필요 (interceptor, datastore) */
        @Header("accessToken") accessToken: String = "",
    ) : Response<List<ChatRoomResult>>

    @GET("chat/messages")
    suspend fun getChatMessages(
        @Query("roomId") roomId: Long,
    ) : Response<List<ChatMessageResult>>
}
