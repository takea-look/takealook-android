package my.takealook.rooms

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import my.takealook.domain.model.ChatRoom
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState
@Parcelize
data object RoomsScreen : Screen {
    data class State(
        val isLoading: Boolean = false,
        val rooms: List<ChatRoom> = emptyList(),
        val error: String? = null,
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        object FetchRooms : Event

    }
}
