package com.mra.rentcar.data.network

import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.data.model.CarResponseModel
import com.mra.rentcar.data.model.UserDataLoginInputModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarsApiService {

    @POST(USER_LOGIN)
    suspend fun loginUser(@Body userData: UserDataLoginInputModel): Response<Any>

    @GET(CARS_LIST)
    suspend fun getCars(): Response<List<CarResponseModel>>

    @POST(BOOKING_CAR)
    suspend fun bookingCar(@Body bookingData: BookingCarInputModel): Response<Any>

}