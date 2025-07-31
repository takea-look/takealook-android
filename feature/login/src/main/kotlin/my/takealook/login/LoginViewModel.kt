package my.takealook.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.takealook.domain.SignInUseCase
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : MviViewModel<LoginUiAction, LoginUiState, LoginSideEffect>(
    initialState = LoginUiState()
) {
    override fun onAction(action: LoginUiAction): Flow<LoginSideEffect> {
        return when (action) {
            is LoginUiAction.SignIn -> flow {
                emit(LoginSideEffect.Loading(true))
                signInUseCase(action.username, action.password)
                    .onSuccess { accessToken ->
                        emit(LoginSideEffect.SignInSuccess(accessToken = accessToken))
                    }
                    .onFailure {
                        emit(LoginSideEffect.Error(it.message ?: "Unknown error"))
                    }
                emit(LoginSideEffect.Loading(false))
            }

            is LoginUiAction.OnPasswordChanged -> flow {
                emit(LoginSideEffect.OnPasswordChanged(action.password))
            }

            is LoginUiAction.OnUsernameChanged -> flow {
                emit(LoginSideEffect.OnUsernameChanged(action.username))
            }
        }
    }

    override fun reduce(
        prevState: LoginUiState,
        currentEvent: LoginSideEffect
    ): LoginUiState {
        return when (currentEvent) {
            is LoginSideEffect.Loading -> prevState.copy(
                isLoading = currentEvent.isLoading
            )
            is LoginSideEffect.OnPasswordChanged -> prevState.copy(
                password = currentEvent.password
            )
            is LoginSideEffect.OnUsernameChanged -> prevState.copy(
                username = currentEvent.username
            )
            is LoginSideEffect.SignInSuccess -> prevState.copy(
                errorMessage = ""
            )
            is LoginSideEffect.Error -> prevState.copy(
                errorMessage = currentEvent.message
            )

        }

    }
}