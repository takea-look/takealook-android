package my.takealook.rooms

import my.takealook.domain.model.ChatRoom
import my.takealook.presentation.SideEffect
import my.takealook.presentation.UiAction
import my.takealook.presentation.UiState

data class RoomsUiState(
    val isLoading: Boolean = false,
    val rooms: List<ChatRoom> = emptyList(),
    val error: String? = null,
) : UiState

sealed interface RoomsSideEffect : SideEffect {
    data class Loading(val isLoading: Boolean) : RoomsSideEffect
    data class OnRoomsFetched(val rooms: List<ChatRoom>) : RoomsSideEffect
    data class Error(val message: String) : RoomsSideEffect
}

sealed interface RoomsUiAction : UiAction {
    object FetchRooms : RoomsUiAction
}