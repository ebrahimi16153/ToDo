package com.github.com.ebrahimi16153.todo.data



// Result class to handel result of dataBase -> wrap data in this class
// in fact sealed class like enum class with more option
sealed class RequestState<out T> {
    data object Idle:RequestState<Nothing>()
    data object Loading:RequestState<Nothing>()
    data class Success<T>(val data:T):RequestState<T>()
    data class Error(val error:Throwable):RequestState<Nothing>()

}