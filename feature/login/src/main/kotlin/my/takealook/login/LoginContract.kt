package my.takealook.login

import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val errorMessage: String = "",
) : UiState

sealed interface LoginSideEffect : SideEffect {
    data class Loading(val isLoading: Boolean) : LoginSideEffect
    data class OnUsernameChanged(val username: String) : LoginSideEffect
    data class OnPasswordChanged(val password: String) : LoginSideEffect
    data class SignInSuccess(val accessToken: String) : LoginSideEffect
    data class Error(val message: String) : LoginSideEffect
}

sealed interface LoginUiAction : UiAction {
    data class SignIn(val username: String, val password: String) : LoginUiAction
    data class OnUsernameChanged(val username: String) : LoginUiAction
    data class OnPasswordChanged(val password: String) : LoginUiAction

}