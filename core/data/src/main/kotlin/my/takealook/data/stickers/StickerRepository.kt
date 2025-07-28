package my.takealook.data.stickers

import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult

interface StickerRepository {
    suspend fun getStickersByCategory(
        categoryId: Int
    ): List<StickerResult>

    suspend fun getCategories(): List<StickerCategoryResult>
}