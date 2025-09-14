package my.takealook.chat

import my.takealook.domain.model.ChatMessage
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val error: String? = null,
) : UiState

sealed interface ChatSideEffect : SideEffect {
    data class Loading(val isLoading: Boolean) : ChatSideEffect
    data class OnMessagesFetched(val messages: List<ChatMessage>) : ChatSideEffect
    data class Error(val message: String) : ChatSideEffect
}

sealed interface ChatUiAction : UiAction {
    data class FetchMessages(val roomId: Long) : ChatUiAction
}