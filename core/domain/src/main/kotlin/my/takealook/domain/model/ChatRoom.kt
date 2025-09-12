package my.takealook.domain.model

import my.takealook.model.chat.ChatRoomResult

data class ChatRoom(
    val id: Long? = null,
    val name: String,
    val isPublic: Boolean,
    val maxParticipants: Int,
    val createdAt: Long,
)

fun ChatRoomResult.asDomainEntity() = ChatRoom(
    id = id ?: 0L,
    name = name,
    isPublic = isPublic,
    maxParticipants = maxParticipants,
    createdAt = createdAt,
)
