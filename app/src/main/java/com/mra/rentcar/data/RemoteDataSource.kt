package com.mra.rentcar.data

import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.data.model.mapper.CarsListResponseMapper
import com.mra.rentcar.data.network.CarsApiService
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.utils.ResponseState
import com.mra.rentcar.utils.toResponseSate
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean>

    suspend fun getCarsList(): ResponseState<List<CarModel>>

    suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean>
}

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: CarsApiService
): RemoteDataSource {

    override suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean> {
        return apiService.loginUser(userData).toResponseSate()
    }

    override suspend fun getCarsList(): ResponseState<List<CarModel>> {
        return apiService.getCars().toResponseSate(CarsListResponseMapper)
    }

    override suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean> {
        return apiService.bookingCar(bookingData).toResponseSate()
    }

}