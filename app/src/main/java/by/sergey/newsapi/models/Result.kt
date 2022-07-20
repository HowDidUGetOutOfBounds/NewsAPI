package by.sergey.newsapi.models

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure<out T : Any>(val msg: String, val data: T?) : Result<T>()
    data class Loading<out T : Any>(val data: T) : Result<T>()
}