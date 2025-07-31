package my.takealook.model

import kotlinx.serialization.json.Json
import my.takealook.ErrorResponse
import okhttp3.ResponseBody

fun ResponseBody.toErrorResponse(): ErrorResponse {
    return Json.decodeFromString<ErrorResponse>(string())
}
