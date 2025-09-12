package my.takealook.rooms

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                emit(RoomsSideEffect.Loading(true))
                getRoomsUseCase()
                    .onSuccess {
                        emit(RoomsSideEffect.OnRoomsFetched(it))
                    }
                    .onFailure {
                        emit(RoomsSideEffect.Error(it.message.toString()))
                    }
                emit(RoomsSideEffect.Loading(false))
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
