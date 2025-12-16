package my.takealook.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import my.takealook.domain.GetStickerCategoryUseCase
import my.takealook.domain.GetStickersByCategoryUseCase
import my.takealook.domain.SignInUseCase
import my.takealook.domain.model.Sticker
import my.takealook.domain.model.StickerCategory
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

class EditorPresenter @AssistedInject constructor(
    @Assisted private val screen : EditorScreen,
    @Assisted private val navigator : Navigator,
    private val getCategoriesUseCate: GetStickerCategoryUseCase,
    private val getStickersUseCase: GetStickersByCategoryUseCase
) : Presenter<EditorScreen.State> {

    @Composable
    override fun present(): EditorScreen.State {
        val scope = rememberCoroutineScope()
        val isLoading = rememberRetained { mutableStateOf(true) }
        val categories = rememberRetained { mutableStateOf(listOf<StickerCategory>()) }
        val stickers = rememberRetained { mutableStateOf(listOf<Sticker>()) }
        val errorMessage = rememberRetained { mutableStateOf("") }
        val isStickerSheetOpen = rememberRetained { mutableStateOf(false) }
        val selectedCategoryTabIndex = rememberRetained { mutableStateOf(0) }

        fun selectCategory(currentCategoryId: Int) {
            val index = categories.value
                .indexOfFirst { it.id == currentCategoryId }
                .coerceAtLeast(0)
            selectedCategoryTabIndex.value = index
        }

        return EditorScreen.State(
            isLoading = isLoading.value,
            categories = categories.value,
            stickers = stickers.value,
            errorMessage = errorMessage.value,
            isStickerSheetOpen = isStickerSheetOpen.value,
            selectedCategoryTabIndex = selectedCategoryTabIndex.value
        ) { event ->
            when(event) {
                is EditorScreen.Event.FetchCategories -> {
                    scope.launch {
                        isLoading.value = true
                        errorMessage.value = ""
                        categories.value = getCategoriesUseCate()
                        isLoading.value = false
                    }
                }

                is EditorScreen.Event.FetchStickers -> {
                    scope.launch {
                        isLoading.value = true
                        errorMessage.value = ""
                        selectCategory(event.categoryId)
                        stickers.value = getStickersUseCase(event.categoryId)
                        isLoading.value = false
                    }
                }

                EditorScreen.Event.OpenStickerSheet -> {
                    isStickerSheetOpen.value = true
                }

                EditorScreen.Event.CloseStickerSheet -> {
                    isStickerSheetOpen.value = false
                }
            }
        }
    }

    @CircuitInject(EditorScreen::class, SingletonComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: EditorScreen, navigator: Navigator): EditorPresenter
    }
}
