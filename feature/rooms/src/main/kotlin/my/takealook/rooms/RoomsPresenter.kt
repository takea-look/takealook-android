package my.takealook.rooms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuitx.effects.LaunchedImpressionEffect
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import my.takealook.domain.GetRoomsUseCase
import my.takealook.domain.model.ChatRoom

class RoomsPresenter @AssistedInject constructor(
    @Assisted private val screen : RoomsScreen,
    @Assisted private val navigator : Navigator,
    private val getRoomsUseCase: GetRoomsUseCase
) : Presenter<RoomsScreen.State> {

    @Composable
    override fun present(): RoomsScreen.State {
        val isLoading = rememberRetained { mutableStateOf(true) }
        val rooms = rememberRetained { mutableStateOf(emptyList<ChatRoom>()) }
        val error = rememberRetained { mutableStateOf("") }

        LaunchedImpressionEffect {
            getRoomsUseCase().collect {
                rooms.value = it
            }
        }

        return RoomsScreen.State(
            isLoading = false,
            rooms = emptyList(),
            error = null
        ) { event ->
            when (event) {
                RoomsScreen.Event.FetchRooms -> {
                    // TODO : 제거 필요..?
                }
            }
        }
    }

    @CircuitInject(RoomsScreen::class, SingletonComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: RoomsScreen, navigator: Navigator): RoomsPresenter
    }
}

