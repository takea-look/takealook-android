package com.easternkite.takealook

import com.easternkite.takealook.model.StickerCategoryResult
import com.easternkite.takealook.model.StickerResult
import retrofit2.Response
import retrofit2.http.GET

interface TklApi {

    @GET("stickers")
    suspend fun getStickers(): Response<List<StickerResult>>

    @GET("sticker-categories")
    suspend fun getStickerCategories(): Response<List<StickerCategoryResult>>
}