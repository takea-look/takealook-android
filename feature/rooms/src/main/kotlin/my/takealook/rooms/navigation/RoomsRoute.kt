package my.takealook.rooms.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import kotlinx.serialization.Serializable
import my.takealook.rooms.RoomsScreen

@Serializable
object RoomsRoute : NavKey

fun EntryProviderBuilder<NavKey>.roomsRoute(
    onRoomClick: (roomId: Long) -> Unit
) {
    entry<RoomsRoute> {
        RoomsScreen(
            onRoomClick = onRoomClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}
