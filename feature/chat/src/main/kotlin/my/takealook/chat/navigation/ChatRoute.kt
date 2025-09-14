package my.takealook.chat.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import kotlinx.serialization.Serializable
import my.takealook.chat.ChatScreen

@Serializable
object ChatRoute : NavKey

fun EntryProviderBuilder<NavKey>.chatRoute() {
    entry<ChatRoute> {
        /* TODO: 세부 UI 구현 필요 */
        ChatScreen()
    }
}
