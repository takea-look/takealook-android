package my.takealook.data.storage

import kotlinx.coroutines.flow.Flow

interface StorageRepository {

    suspend fun getPresignedUrl(path: String): Flow<String>
}
