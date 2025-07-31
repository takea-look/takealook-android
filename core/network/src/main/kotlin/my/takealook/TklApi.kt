package my.takealook

import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import my.takealook.model.login.LoginBody
import my.takealook.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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
}
