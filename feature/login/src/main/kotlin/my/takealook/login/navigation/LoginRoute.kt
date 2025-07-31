package my.takealook.login.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import kotlinx.serialization.Serializable
import my.takealook.login.LoginScreen

@Serializable
object LoginRoute : NavKey

fun EntryProviderBuilder<NavKey>.loginRoute(
    onSignInSuccess: (accessToken: String) -> Unit = {}
) {
    entry<LoginRoute> {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            onSignInSuccess = onSignInSuccess
        )
    }

}