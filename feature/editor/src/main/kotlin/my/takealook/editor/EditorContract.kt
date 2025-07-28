package my.takealook.editor

import my.takealook.domain.model.Sticker
import my.takealook.domain.model.StickerCategory
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

data class EditorUiState(
    val isLoading: Boolean = false,
    val categories: List<StickerCategory> = listOf(),
    val stickers: List<Sticker> = listOf(),
    val isStickerSheetOpen: Boolean = false,
    val selectedCategoryTabIndex: Int = 0,
    val errorMessage: String = "",
) : UiState

sealed interface EditorSideEffect : SideEffect {
    data class Loading(val isLoading: Boolean) : EditorSideEffect
    data class RequestCategories(val data: List<StickerCategory>) : EditorSideEffect
    data class RequestStickers(val data: List<Sticker>) : EditorSideEffect
    data class SelectCategory(val categoryId: Int) : EditorSideEffect
    object OpenStickerSheet : EditorSideEffect
    object CloseStickerSheet : EditorSideEffect
    data class Error(val message: String) : EditorSideEffect
}

sealed interface EditorUiAction : UiAction {
    object OpenStickerSheet : EditorUiAction
    object CloseStickerSheet : EditorUiAction
    object FetchCategories : EditorUiAction
    data class FetchStickers(val categoryId: Int) : EditorUiAction
}
