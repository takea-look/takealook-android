package com.easternkite.takealook

import retrofit2.Response

inline fun <T>Response<T>.onSuccess(block: (T) -> Unit): Response<T> = apply {
    if (isSuccessful) body()?.also(block)
}

inline fun <T>Response<T>.onFailure(block: (Throwable) -> Unit): Response<T> = apply {
    if (!isSuccessful) {
        val errorMessage = errorBody()?.string() ?: "Unknown Error"
        errorBody()?.let { block(Throwable(errorMessage)) }
    }
}
