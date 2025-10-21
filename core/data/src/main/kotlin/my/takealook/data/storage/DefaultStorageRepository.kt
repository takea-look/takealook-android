package my.takealook.data.storage

import kotlinx.coroutines.flow.firstOrNull
import my.takealook.TklApi
import my.takealook.datastore.TklDataStore
import my.takealook.model.toErrorResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class DefaultStorageRepository @Inject constructor(
    private val api: TklApi,
    private val dataStore: TklDataStore
) : StorageRepository {

    override suspend fun getPresignedUrl(path: String): Result<String> {
        val accessToken = dataStore.accessToken.firstOrNull()
            ?: return Result.failure(Exception("accessToken is null"))

        val response = api.getPresignedStorageUrl(path, accessToken)
        if (response.isSuccessful) {
            return Result.success(response.body()!!.url)
        } else {
            return Result.failure(response.errorBody()?.toErrorResponse()!!)
        }
    }

    override suspend fun uploadImage(presignedUrl: String, bytes: ByteArray) {
        val contentType = "image/jpeg"
        val file = bytes.toRequestBody(contentType.toMediaType())
        api.uploadImage(
            presignedUrl = presignedUrl,
            file = file,
            contentType = contentType
        )
    }
}
