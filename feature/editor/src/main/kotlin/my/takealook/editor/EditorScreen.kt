package my.takealook.editor

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import my.takealook.R
import my.takealook.memento.MementoEditor
import my.takealook.memento.attachImage
import my.takealook.memento.rememberMementoController

@Composable
fun EditorScreen(
    modifier: Modifier = Modifier,
    viewModel: EditorViewModel = hiltViewModel(),
    onImageCaptured: (ImageBitmap) -> Unit = {},
    mainContent: @Composable BoxScope.() -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val controller = rememberMementoController()

    LaunchedEffect(Unit) {
        viewModel.send(EditorUiAction.FetchCategories)
        viewModel.send(EditorUiAction.FetchStickers(categoryId = 1))
    }

    Box(modifier = modifier) {
        MementoEditor(
            modifier = Modifier.fillMaxSize(),
            controller = controller,
            onImageCaptured = onImageCaptured,
            mainContent = mainContent
        )

        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                .padding(3.dp)
                .align(Alignment.TopEnd)
                .clickable { viewModel.send(EditorUiAction.OpenStickerSheet) },
            model = R.drawable.ic_paw,
            contentDescription = "paw"
        )
    }

    if (uiState.isStickerSheetOpen) {
        StickerSheet(
            onDismiss = { viewModel.send(EditorUiAction.CloseStickerSheet) },
            selectedCategoryTabIndex = uiState.selectedCategoryTabIndex,
            onCategorySelected = { viewModel.send(EditorUiAction.FetchStickers(it.id)) },
            onStickerClicked = {
                controller.attachImage {
                    AsyncImage(
                        model = it.stickerUrl,
                        contentDescription = "sticker attached"
                    )
                }
                viewModel.send(EditorUiAction.CloseStickerSheet)
            },
            categories = uiState.categories,
            stickers = uiState.stickers
        )
    }
}
