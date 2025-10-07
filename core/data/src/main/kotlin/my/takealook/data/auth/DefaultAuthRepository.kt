package my.takealook.data.auth

import my.takealook.TklApi
import my.takealook.datastore.TklDataStore
import my.takealook.model.login.LoginBody
import my.takealook.model.toErrorResponse
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val tklApi: TklApi,
    private val tklDataStore: TklDataStore
) : AuthRepository {

    override suspend fun signIn(username: String, password: String): Result<String> {
        val body = LoginBody(username, password)
        val response = tklApi.signIn(body)

        return if (response.isSuccessful) {
            val body = response.body()!!
            tklDataStore.setAccessToken(body.accessToken)
            Result.success(body.accessToken)
        } else {
            Result.failure(response.errorBody()?.toErrorResponse() ?: Exception("something went wrong"))
        }
    }

    override suspend fun signUp(username: String, password: String): Result<Unit> {
        val body = LoginBody(username, password)
        val response = tklApi.signUp(body)

        return if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(response.errorBody()?.toErrorResponse() ?: Exception("something went wrong"))
        }
    }
}
