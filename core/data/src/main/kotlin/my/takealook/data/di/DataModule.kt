package my.takealook.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.takealook.data.stickers.DefaultStickerRepository
import my.takealook.data.stickers.StickerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindStickerRepository(impl: DefaultStickerRepository): StickerRepository
}
