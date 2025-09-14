package my.takealook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import my.takealook.theme.TklTheme
import dagger.hilt.android.AndroidEntryPoint
import my.takealook.chat.navigation.chatRoute
import my.takealook.editor.navigation.editorScreenRoute
import my.takealook.login.navigation.LoginRoute
import my.takealook.login.navigation.loginRoute
import my.takealook.rooms.navigation.RoomsRoute
import my.takealook.rooms.navigation.roomsRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TklTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val backStack = rememberNavBackStack(LoginRoute)

                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            loginRoute(
                                onSignInSuccess = {
                                    backStack.add(RoomsRoute)
                                }
                            )
                            editorScreenRoute()
                            roomsRoute(onRoomClick = { /** TODO : 채팅방 상세화면으로 이동 필요 */ })
                            chatRoute()
                        }
                    )
                }
            }
        }
    }
}