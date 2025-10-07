package my.takealook.rooms

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import my.takealook.domain.GetRoomsUseCase
import my.takealook.presentation.MviViewModel
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase
) : MviViewModel<RoomsUiAction, RoomsUiState, RoomsSideEffect>(
    initialState = RoomsUiState()
) {
    init {
        send(RoomsUiAction.FetchRooms)
    }

    override fun onAction(action: RoomsUiAction): Flow<RoomsSideEffect> {
        return when (action) {
            RoomsUiAction.FetchRooms -> flow {
                val flow = getRoomsUseCase()
                    .onStart { emit(RoomsSideEffect.Loading(true)) }
                    .map { RoomsSideEffect.OnRoomsFetched(it) }
                    .catch { emit(RoomsSideEffect.Error(it.message ?: "")) }
                    .onCompletion { emit(RoomsSideEffect.Loading(false)) }

                emitAll(flow)
            }
        }
    }

    override fun reduce(
        prevState: RoomsUiState,
        currentEvent: RoomsSideEffect
    ): RoomsUiState {
        return when(currentEvent) {
            is RoomsSideEffect.Loading -> prevState.copy(isLoading = currentEvent.isLoading)
            is RoomsSideEffect.OnRoomsFetched -> prevState.copy(rooms = currentEvent.rooms)
            is RoomsSideEffect.Error -> prevState.copy(error = currentEvent.message)
        }
    }
}
