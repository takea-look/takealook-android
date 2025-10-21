package my.takealook.chat

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import my.takealook.component.TklAttachButton
import java.io.ByteArrayOutputStream
import java.io.InputStream

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Box {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(state.messages, key = { it.id ?: it.createdAt }) {
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = "message of ${it.sender.name}",
                        modifier = Modifier
                    )
                }
            }

            TklAttachButton(
                onItemsAttached = { images ->
                    val firstImage = images.firstOrNull() ?: return@TklAttachButton
                    val bytes = firstImage.toBytes(context) ?: return@TklAttachButton
                    /* TODO : 파일 경로 지정 로직, 여러개 파일 첨부 로직 추가 필요하다 */
                    viewModel.send(ChatUiAction.UploadImage("test/test.jpg", bytes))
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}

fun Uri.toBytes(context: Context): ByteArray? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(this)
        val buffer = ByteArrayOutputStream()
        val data = ByteArray(1024)
        var byteRead: Int

        while(inputStream?.read(data).also { byteRead = it ?: - 1 } != -1) {
            buffer.write(data, 0, byteRead)
        }
        inputStream?.close()
        buffer.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}