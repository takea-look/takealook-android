package my.takealook.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import my.takealook.domain.SignInUseCase
import my.takealook.editor.EditorScreen

class LoginPresenter @AssistedInject constructor(
    @Assisted private val screen : LoginScreen,
    @Assisted private val navigator : Navigator,
    private val signInUseCase: SignInUseCase,
) : Presenter<LoginScreen.State> {

    @Composable
    override fun present(): LoginScreen.State {
        val scope = rememberCoroutineScope()

        val isLoading = rememberRetained { mutableStateOf(true) }
        val userName = rememberRetained { mutableStateOf("") }
        val password = rememberRetained { mutableStateOf("") }
        val errorMessage = rememberRetained { mutableStateOf("") }

        return LoginScreen.State(
            isLoading = false,
            username = userName.value,
            password = password.value,
            errorMessage = ""
        ) { event ->
            when (event) {
                is LoginScreen.Event.SignIn -> {
                    scope.launch {
                        isLoading.value = true
                        errorMessage.value = ""

                        signInUseCase(userName.value, password.value)
                            .onSuccess { accessToken ->
                                navigator.goTo(EditorScreen)
                            }
                            .onFailure {
                                errorMessage.value = it.message ?: ""
                            }

                        isLoading.value = false
                    }
                }
                is LoginScreen.Event.OnPasswordChanged -> {
                    password.value = event.password
                }
                is LoginScreen.Event.OnUsernameChanged -> {
                    userName.value = event.username
                }
            }
        }
    }

    @CircuitInject(LoginScreen::class, SingletonComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: LoginScreen, navigator: Navigator): LoginPresenter
    }
}