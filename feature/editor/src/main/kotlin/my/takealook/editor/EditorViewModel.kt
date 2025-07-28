package my.takealook.editor

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.takealook.domain.GetStickerCategoryUseCase
import my.takealook.domain.GetStickersByCategoryUseCase
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val getCategoriesUseCate: GetStickerCategoryUseCase,
    private val getStickersUseCase: GetStickersByCategoryUseCase
) : MviViewModel<EditorUiAction, EditorUiState, EditorSideEffect>(
    initialState = EditorUiState()
) {
    override fun onAction(action: EditorUiAction): Flow<EditorSideEffect> {
        return when(action) {
            EditorUiAction.FetchCategories -> flow {
                emit(EditorSideEffect.Loading(true))
                val categories = getCategoriesUseCate()
                emit(EditorSideEffect.RequestCategories(categories))
                emit(EditorSideEffect.Loading(true))
            }
            is EditorUiAction.FetchStickers -> flow {
                emit(EditorSideEffect.Loading(true))
                emit(EditorSideEffect.SelectCategory(action.categoryId))
                val stickers = getStickersUseCase(action.categoryId)
                emit(EditorSideEffect.RequestStickers(stickers))
                emit(EditorSideEffect.Loading(false))
            }

            EditorUiAction.CloseStickerSheet -> flow {
                emit(EditorSideEffect.SelectCategory(0))
                emit(EditorSideEffect.CloseStickerSheet)
            }
            EditorUiAction.OpenStickerSheet -> flow {
                emit(EditorSideEffect.OpenStickerSheet)
            }
        }
    }

    override fun reduce(
        prevState: EditorUiState,
        currentEvent: EditorSideEffect
    ): EditorUiState {
        return when(currentEvent) {
            is EditorSideEffect.Loading -> prevState.copy(isLoading = currentEvent.isLoading)
            is EditorSideEffect.RequestCategories -> prevState.copy(categories = currentEvent.data)
            is EditorSideEffect.RequestStickers -> prevState.copy(stickers = currentEvent.data)
            is EditorSideEffect.Error -> prevState.copy(errorMessage = currentEvent.message)
            is EditorSideEffect.SelectCategory -> {
                val index = prevState
                    .categories
                    .indexOfFirst { it.id == currentEvent.categoryId }
                    .coerceAtLeast(0)
                prevState.copy(selectedCategoryTabIndex = index)
            }
            is EditorSideEffect.CloseStickerSheet -> prevState.copy(isStickerSheetOpen = false)
            is EditorSideEffect.OpenStickerSheet -> prevState.copy(isStickerSheetOpen = true)
        }
    }
}