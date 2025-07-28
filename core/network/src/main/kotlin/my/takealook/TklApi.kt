package my.takealook

import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TklApi {

    @GET("stickers")
    suspend fun getStickers(
        @Query("categoryId") categoryId: Int? = null
    ): List<StickerResult>

    @GET("sticker-categories")
    suspend fun getStickerCategories(): List<StickerCategoryResult>
}