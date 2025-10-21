package my.takealook.data.storage

interface StorageRepository {

    suspend fun getPresignedUrl(path: String): Result<String>

    suspend fun uploadImage(presignedUrl: String, bytes: ByteArray)
}
