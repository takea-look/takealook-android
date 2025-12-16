package my.takealook.login

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object LoginScreen : Screen {
    data class State(
        val isLoading: Boolean = false,
        val username: String = "",
        val password: String = "",
        val errorMessage: String = "",
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        object SignIn : Event
        data class OnUsernameChanged(val username: String) : Event
        data class OnPasswordChanged(val password: String) : Event
    }
}
