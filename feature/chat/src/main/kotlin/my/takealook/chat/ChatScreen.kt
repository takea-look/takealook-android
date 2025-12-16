package my.takealook.chat

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import my.takealook.domain.model.ChatMessage


@Parcelize
data object ChatScreen : Screen {
    data class State(
        val isLoading: Boolean = false,
        val messages: List<ChatMessage> = emptyList(),
        val errorMessage: String = "",
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class FetchMessages(val roomId: Long) : Event
        data class UploadImage(val path: String,val bytes: ByteArray) : Event {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as UploadImage

                if (!bytes.contentEquals(other.bytes)) return false

                return true
            }

            override fun hashCode(): Int {
                return bytes.contentHashCode()
            }
        }
    }
}