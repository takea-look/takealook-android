package my.takealook.data.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import my.takealook.TklApi
import my.takealook.datastore.TklDataStore
import my.takealook.model.toErrorResponse
import javax.inject.Inject

class DefaultStorageRepository @Inject constructor(
    private val api: TklApi,
    private val dataStore: TklDataStore
) : StorageRepository {

    override suspend fun getPresignedUrl(path: String): Flow<String> {
        return dataStore.accessToken.flatMapLatest {
            flow {
                val response = api.getPresignedStorageUrl(path)
                if (response.isSuccessful) {
                    emit(response.body()!!.url)
                } else {
                    throw response.errorBody()?.toErrorResponse()!!
                }
            }
        }
    }
}
