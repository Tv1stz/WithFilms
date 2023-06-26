package com.example.withfilms.util

sealed class Request<T> {
    data class Success<T>(val data: T): Request<T>()
    data class Error<T>(val message: Throwable?): Request<T>()
    class Loading<T>: Request<T>()
}