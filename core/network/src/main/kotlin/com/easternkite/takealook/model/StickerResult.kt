package com.easternkite.takealook.model

data class StickerResult(
    val id: Int? = null,
    val name: String,
    val iconUrl: String,
    val thumbnailUrl: String,
    val categoryId: Int
)
