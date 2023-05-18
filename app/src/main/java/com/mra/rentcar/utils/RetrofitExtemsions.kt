package com.mra.rentcar.utils

import com.mra.rentcar.data.model.BaseMapper
import retrofit2.Response

fun <T, R> Response<T>.toResponseSate(
    mapper: BaseMapper<T, R>? = null
): ResponseState<R> {
    try {
        if (!this.isSuccessful)
            return ResponseState.Error(message = this.message())

        return if (this.body() != null) {
            when (this.code()) {
                404 -> ResponseState.Error(message = "Please try later")
                402 -> ResponseState.Error(message = "Authentication Error")
                in 300..399 -> ResponseState.Error(message = this.message())
                in 400..499 -> ResponseState.Error(message = this.message())
                in 500..599 -> ResponseState.Error(message = "Server note responding")
                else -> ResponseState.Success(data = mapper?.mapTo(this.body()))
            }
        } else {
            ResponseState.Error(message = "System Error")
        }
    } catch (e: Exception) {
        return ResponseState.Error(message = e.message)
    }
}