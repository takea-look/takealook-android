package my.takealook.editor

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import my.takealook.domain.model.ChatMessage
import my.takealook.domain.model.Sticker
import my.takealook.domain.model.StickerCategory
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

@Parcelize
data object EditorScreen : Screen {
    data class State(
        val isLoading: Boolean = false,
        val categories: List<StickerCategory> = listOf(),
        val stickers: List<Sticker> = listOf(),
        val isStickerSheetOpen: Boolean = false,
        val selectedCategoryTabIndex: Int = 0,
        val errorMessage: String = "",
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        object OpenStickerSheet : Event
        object CloseStickerSheet : Event
        object FetchCategories : Event
        data class FetchStickers(val categoryId: Int) : Event
    }
}