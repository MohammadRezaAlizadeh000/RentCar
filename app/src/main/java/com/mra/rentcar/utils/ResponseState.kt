package com.mra.rentcar.utils

sealed class ResponseState<T>(val data: T?,  val message: String?) {

    class Success<T>(data: T?) : ResponseState<T>(data, message = null)
    class Error<T>(message: String?) : ResponseState<T>(data = null, message = message)
    class Loading<T>: ResponseState<T>(null, null)
}
