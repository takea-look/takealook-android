package my.takealook.chat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.takealook.domain.GetChatMessagesUseCase
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatMessagesUseCase: GetChatMessagesUseCase
) : MviViewModel<ChatUiAction, ChatUiState, ChatSideEffect>(
    ChatUiState()
) {
    override fun onAction(action: ChatUiAction): Flow<ChatSideEffect> {
        return when (action) {
            is ChatUiAction.FetchMessages -> flow {
                emit(ChatSideEffect.Loading(true))
                getChatMessagesUseCase(action.roomId)
                    .onSuccess {
                        emit(ChatSideEffect.OnMessagesFetched(it))
                    }.onFailure {
                        emit(ChatSideEffect.Error(it.message ?: ""))
                    }
                emit(ChatSideEffect.Loading(false))
            }
        }
    }

    override fun reduce(
        prevState: ChatUiState,
        currentEvent: ChatSideEffect
    ): ChatUiState {
        return when (currentEvent) {
            is ChatSideEffect.OnMessagesFetched -> prevState.copy(
                messages = listOf()
            )
            else -> prevState
        }

    }
}