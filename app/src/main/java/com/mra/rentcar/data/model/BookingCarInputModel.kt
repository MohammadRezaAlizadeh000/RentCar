package com.mra.rentcar.data.model

import com.google.gson.annotations.SerializedName

data class BookingCarInputModel(
    @SerializedName("booking_id")
    val bookingId: Int? = 0,
    @SerializedName("car_id")
    val carId: Int?,
    @SerializedName("user_name")
    val username: String?,
    @SerializedName("startdate")
    val startDate: String?,
    @SerializedName("enddate")
    val endDate: String?,
)
