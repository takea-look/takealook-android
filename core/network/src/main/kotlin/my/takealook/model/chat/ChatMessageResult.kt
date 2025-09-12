package my.takealook.model.chat

data class ChatMessageResult(
    val id: Long? = null,
    val roomId: Long = 0L,
    val senderId: Long = 0L,
    val imageUrl: String = "",
    val replyToId: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
)
