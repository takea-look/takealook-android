package my.takealook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

abstract class MviViewModel<ACTION : UiAction, STATE : UiState, EFFECT : SideEffect>(
    initialState: STATE
): ViewModel() {
    private val actionChannel = Channel<ACTION>(Channel.Factory.BUFFERED)
    private val effectChannel = Channel<EFFECT>(Channel.Factory.BUFFERED)

    val effect = effectChannel
        .receiveAsFlow()

    val state = actionChannel
        .receiveAsFlow()
        .flatMapConcat(::onAction)
        .onEach(effectChannel::send)
        .runningFold(initialState, ::reduce)
        .stateIn(viewModelScope, SharingStarted.Companion.Eagerly, initialState)

    fun send(action: ACTION) {
        // TODO: 이벤트를 많이 보내다보면 더이상 이벤트 수신이 안되는 이슈가 있음
        actionChannel.trySend(action)
    }

    abstract fun onAction(action: ACTION): Flow<EFFECT>
    abstract fun reduce(prevState: STATE, currentEvent: EFFECT): STATE
}
