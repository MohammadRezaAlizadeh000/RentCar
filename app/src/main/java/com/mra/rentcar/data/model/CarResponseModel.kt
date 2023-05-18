package com.mra.rentcar.data.model

import com.google.gson.annotations.SerializedName

data class CarResponseModel(
    @SerializedName("user_name")
    val username: String?,
    @SerializedName("car_id")
    val carId: Int?,
    val make: String?,
    val model: String?,
    val year: Int?,
    val price: Int?,
    val location: String?,
)
