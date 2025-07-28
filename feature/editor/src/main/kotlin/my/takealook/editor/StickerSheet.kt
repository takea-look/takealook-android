package my.takealook.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import my.takealook.domain.model.Sticker
import my.takealook.domain.model.StickerCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickerSheet(
    onDismiss: () -> Unit,
    selectedCategoryTabIndex: Int,
    onCategorySelected: (StickerCategory) -> Unit,
    onStickerClicked: (Sticker) -> Unit,
    categories: List<StickerCategory> = listOf(),
    stickers: List<Sticker> = listOf(),
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.height(500.dp)
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedCategoryTabIndex,
            ) {
                categories.forEach {
                    Tab (
                        selected = selectedCategoryTabIndex == it.id,
                        onClick = { onCategorySelected(it) },
                        content = {
                            AsyncImage(
                                modifier = Modifier.size(50.dp),
                                model = it.thumbnailUrl,
                                contentDescription = it.contentDescription
                            )
                        }
                    )
                }
            }

            LazyVerticalGrid(columns = GridCells.Fixed(5)) {
                items(stickers) { sticker ->
                    AsyncImage(
                        model = sticker.thumbnailUrl,
                        contentDescription = "sticker",
                        modifier = Modifier.clickable { onStickerClicked(sticker) }
                    )
                }
            }
        }
    }
}
