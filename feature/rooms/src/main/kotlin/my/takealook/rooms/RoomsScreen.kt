package my.takealook.rooms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.takealook.theme.TklTheme

@Composable
fun RoomsScreen(
    onRoomClick: (roomId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoomsViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items = uiState.rooms, key = { it.id ?: it.createdAt }) { room ->
                if (!room.isPublic) return@items

                RoomItem(
                    title = room.name,
                    modifier = Modifier
                        .clickable {
                            if (room.id != null) {
                                onRoomClick(room.id!!)
                            }
                        }
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun RoomItem(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun RoomItemPreview() {
    TklTheme {
        RoomItem(title = "roomName")
    }
}

