package my.takealook.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.takealook.data.auth.AuthRepository
import my.takealook.data.auth.DefaultAuthRepository
import my.takealook.data.chat.ChatRepository
import my.takealook.data.chat.DefaultChatRepository
import my.takealook.data.stickers.DefaultStickerRepository
import my.takealook.data.stickers.StickerRepository
import my.takealook.data.storage.DefaultStorageRepository
import my.takealook.data.storage.StorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindStickerRepository(impl: DefaultStickerRepository): StickerRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: DefaultAuthRepository): AuthRepository

    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: DefaultChatRepository): ChatRepository

    @Binds
    @Singleton
    abstract fun bindStorageRepository(impl: DefaultStorageRepository): StorageRepository
}
