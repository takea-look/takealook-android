package my.takealook.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.takealook.domain.GetChatMessagesUseCase
import my.takealook.domain.UploadImageUseCase
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    private val uploadImageUseCase: UploadImageUseCase
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
            is ChatUiAction.UploadImage -> flow {
                uploadImageUseCase(action.path, action.bytes)
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