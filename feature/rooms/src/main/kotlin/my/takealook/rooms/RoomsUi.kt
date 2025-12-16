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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent
import my.takealook.theme.TklTheme

@CircuitInject(screen = RoomsScreen::class, scope = SingletonComponent::class)
@Composable
fun RoomsUi(
    state: RoomsScreen.State,
    modifier: Modifier = Modifier,
    onRoomClick: (roomId: Long) -> Unit = {}, // TODO : Room Click 이벤트는 State로 분리 필요
) {
    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items = state.rooms, key = { it.id ?: it.createdAt }) { room ->
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

