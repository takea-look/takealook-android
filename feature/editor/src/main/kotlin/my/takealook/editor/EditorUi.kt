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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent
import my.takealook.memento.MementoEditor
import my.takealook.memento.attachImage
import my.takealook.memento.rememberMementoController

@CircuitInject(EditorScreen::class, SingletonComponent::class)
@Composable
fun EditorUi(
    state: EditorScreen.State,
    modifier: Modifier = Modifier,
    onImageCaptured: (ImageBitmap) -> Unit = {},
    mainContent: @Composable BoxScope.() -> Unit = {} // TODO : 메인 컨텐츠 Circuit State 통해 넘기도록 수정 필요
) {
    val controller = rememberMementoController()

    LaunchedEffect(Unit) {
        state.eventSink(EditorScreen.Event.FetchCategories)
        state.eventSink(EditorScreen.Event.FetchStickers(categoryId = 1))
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
                .clickable { state.eventSink(EditorScreen.Event.OpenStickerSheet) },
            model = my.takealook.R.drawable.ic_paw,
            contentDescription = "paw"
        )
    }

    if (state.isStickerSheetOpen) {
        StickerSheet(
            onDismiss = { state.eventSink(EditorScreen.Event.CloseStickerSheet) },
            selectedCategoryTabIndex = state.selectedCategoryTabIndex,
            onCategorySelected = { state.eventSink(EditorScreen.Event.FetchStickers(it.id)) },
            onStickerClicked = {
                controller.attachImage {
                    AsyncImage(
                        model = it.stickerUrl,
                        contentDescription = "sticker attached"
                    )
                }
                state.eventSink(EditorScreen.Event.CloseStickerSheet)
            },
            categories = state.categories,
            stickers = state.stickers
        )
    }
}
