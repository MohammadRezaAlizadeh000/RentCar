package com.mra.rentcar.data.model

interface BaseMapper<T, R> {
    fun mapTo(data: T?): R
}