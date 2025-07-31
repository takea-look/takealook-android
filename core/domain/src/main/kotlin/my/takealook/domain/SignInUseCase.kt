package my.takealook.domain

import my.takealook.data.auth.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<String> {
        return authRepository.signIn(username, password)
    }
}
