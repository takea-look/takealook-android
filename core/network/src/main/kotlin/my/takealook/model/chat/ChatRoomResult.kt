package my.takealook.model.chat

data class ChatRoomResult(
    val id: Long? = null,
    val name: String,
    val isPublic: Boolean,
    val maxParticipants: Int,
    val createdAt: Long,
)
