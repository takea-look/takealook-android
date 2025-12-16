package my.takealook.chat

import android.os.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
import my.takealook.domain.GetChatMessagesUseCase
import my.takealook.domain.SignInUseCase
import my.takealook.domain.UploadImageUseCase
import my.takealook.domain.model.ChatMessage
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

class ChatPresenter @AssistedInject constructor(
    @Assisted private val screen : ChatScreen,
    @Assisted private val navigator : Navigator,
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : Presenter<ChatScreen.State> {

    @Composable
    override fun present(): ChatScreen.State {
        val scope = rememberCoroutineScope()

        val isLoading = rememberRetained { mutableStateOf(true) }
        val errorMessage = rememberRetained { mutableStateOf("") }
        val messages = rememberRetained { mutableStateOf<List<ChatMessage>>(listOf()) }

        return ChatScreen.State(
            isLoading = isLoading.value,
            messages = messages.value,
            errorMessage = ""
        ) { event ->
            when (event) {
                is ChatScreen.Event.FetchMessages -> {
                    scope.launch {
                        isLoading.value = true
                        errorMessage.value = ""

                        getChatMessagesUseCase(roomId = event.roomId)
                            .onSuccess {
                                messages.value = it
                            }
                            .onFailure {
                                errorMessage.value = it.message ?: ""
                            }

                        isLoading.value = false
                    }
                }
                is ChatScreen.Event.UploadImage -> {
                    scope.launch {
                        isLoading.value = true
                        errorMessage.value = ""
                        uploadImageUseCase(event.path, event.bytes)
                        isLoading.value = false
                    }
                }
            }
        }
    }

    @CircuitInject(ChatScreen::class, SingletonComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: ChatScreen, navigator: Navigator): ChatPresenter
    }
}
