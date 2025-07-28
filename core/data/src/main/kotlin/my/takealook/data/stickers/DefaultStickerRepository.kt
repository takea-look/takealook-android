package my.takealook.data.stickers

import my.takealook.TklApi
import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import javax.inject.Inject

class DefaultStickerRepository @Inject constructor(
    private val api: TklApi
) : StickerRepository {
    override suspend fun getStickersByCategory(categoryId: Int): List<StickerResult> {
        return api.getStickers(categoryId)
    }

    override suspend fun getCategories(): List<StickerCategoryResult> {
        return api.getStickerCategories()
    }
}
