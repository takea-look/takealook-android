package my.takealook.data.auth

interface AuthRepository {

    suspend fun signIn(username: String, password: String): Result<String>

    suspend fun signUp(username: String, password: String): Result<Unit>
}