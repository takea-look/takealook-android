package my.takealook

import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import de.jensklingenberg.ktorfit.http.GET

interface TklApi {

    @GET("stickers")
    suspend fun getStickers(): List<StickerResult>

    @GET("sticker-categories")
    suspend fun getStickerCategories(): List<StickerCategoryResult>
}