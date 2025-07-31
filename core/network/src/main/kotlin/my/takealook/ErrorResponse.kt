package my.takealook

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: Int,
    val reason: String,
    override val message: String
): Exception(message)

