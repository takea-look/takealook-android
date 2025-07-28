package my.takealook.domain

import my.takealook.data.stickers.StickerRepository
import my.takealook.domain.model.asDomainEntity
import my.takealook.model.StickerResult
import javax.inject.Inject

class GetStickersByCategoryUseCase @Inject constructor(
    private val stickerRepository: StickerRepository
) {
    suspend operator fun invoke(categoryId: Int) = stickerRepository
        .getStickersByCategory(categoryId = categoryId)
        .map(StickerResult::asDomainEntity)
}
