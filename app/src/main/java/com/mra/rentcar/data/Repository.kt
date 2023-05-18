package com.mra.rentcar.data

import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.utils.ResponseState
import javax.inject.Inject

interface Repository {

    suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean>

    suspend fun getCarsList(): ResponseState<List<CarModel>>

    suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean>

}

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
//    private val mockDataSource: MockDataSource
) : Repository {
    override suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean> {
        return remoteDataSource.userLogin(userData)
//        return mockDataSource.userLogin(userData)
    }

    override suspend fun getCarsList(): ResponseState<List<CarModel>> {
        return remoteDataSource.getCarsList()
//        return mockDataSource.getCarsList()
    }

    override suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean> {
        return remoteDataSource.bookingCar(bookingData)
//        return mockDataSource.bookingCar(bookingData)
    }

}