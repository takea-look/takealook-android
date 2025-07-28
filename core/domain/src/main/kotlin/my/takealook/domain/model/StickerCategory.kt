package my.takealook.domain.model

import my.takealook.model.StickerCategoryResult

data class StickerCategory(
    val id: Int = 0,
    val thumbnailUrl: String = "",
    val contentDescription: String = ""
)

fun StickerCategoryResult.asDomainEntity() : StickerCategory = StickerCategory(
    id = id ?: 0,
    thumbnailUrl = thumbnailUrl,
    contentDescription = name
)
