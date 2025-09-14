package my.takealook.domain.model

import my.takealook.model.chat.ChatMessageResult

data class ChatMessage(
    val id: Long? = null,
    val sender: ChatUser = ChatUser(),
    val imageUrl: String = "",
    val replyToId: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
)

data class ChatUser(
    val id: Long = 0L,
    val name: String = "",
    val imageUrl: String = "",
)

fun ChatMessageResult.asDomainEntity(chatUser: ChatUser) = ChatMessage(
    id = id ?: 0L,
    sender = chatUser,
    imageUrl = imageUrl,
    replyToId = replyToId,
    createdAt = createdAt,
)
