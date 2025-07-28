package my.takealook.domain

import my.takealook.data.stickers.StickerRepository
import my.takealook.domain.model.asDomainEntity
import my.takealook.model.StickerCategoryResult
import my.takealook.model.StickerResult
import javax.inject.Inject

class GetStickerCategoryUseCase @Inject constructor(
    private val stickerRepository: StickerRepository
) {
    suspend operator fun invoke() = stickerRepository
        .getCategories()
        .map(StickerCategoryResult::asDomainEntity)
}
