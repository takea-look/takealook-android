package my.takealook.domain.model

import my.takealook.model.StickerResult

data class Sticker(
    val id: Int = 0,
    val stickerUrl: String = "",
    val thumbnailUrl: String = "",
)

fun StickerResult.asDomainEntity() : Sticker = Sticker(
    id = id ?: 0,
    stickerUrl = iconUrl,
    thumbnailUrl = thumbnailUrl
)
