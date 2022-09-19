package com.example.kabar.utils


sealed class KabarResult<T>(
    val data:T? = null,
    val message:String? = null
) {
    class Success<T>(data:T) : KabarResult<T>(data)
    class Error<T>(message:String):KabarResult<T>(message = message)
    class Loading<T> : KabarResult<T>()
}