package my.takealook.editor.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import coil3.compose.AsyncImage
import kotlinx.serialization.Serializable
import my.takealook.editor.EditorScreen

@Serializable
data class EditorScreenRoute(
    val imageUrl: String
) : NavKey

fun EntryProviderBuilder<NavKey>.editorScreenRoute() {
    entry<EditorScreenRoute> {
        EditorScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            onImageCaptured = {},
            mainContent = {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = "main content"
                )
            }
        )
    }
}
