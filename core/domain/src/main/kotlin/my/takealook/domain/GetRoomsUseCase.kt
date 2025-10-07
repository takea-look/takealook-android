package my.takealook.domain

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import my.takealook.data.chat.ChatRepository
import my.takealook.domain.model.asDomainEntity
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke() = flow {
        val flow = chatRepository
            .getRooms()
            .take(1)
            .map { it.map { it.asDomainEntity() } }
        emitAll(flow)
    }
}
