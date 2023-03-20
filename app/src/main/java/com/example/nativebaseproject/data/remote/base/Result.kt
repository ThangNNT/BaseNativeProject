package com.example.nativebaseproject.data.remote.base

sealed class Result<out T: Any> {
    /**
     * use for initial case
     */
    object Empty : Result<Nothing>()
    class Success <out T: Any>(val data: T?) : Result<T>()
    class Error(val errorModel: ErrorResponse) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> {
                data.toString()
            }
            is Error -> {
                (this).errorModel.toString()
            }
            is Loading -> {
                "Loading"
            }
            is Empty -> "Empty"
        }
    }
}