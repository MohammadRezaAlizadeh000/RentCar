package com.mra.rentcar.data

import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.utils.ResponseState
import javax.inject.Inject

interface MockDataSource {

    suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean>

    suspend fun getCarsList(): ResponseState<List<CarModel>>

    suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean>
}

class MockDataSourceImpl @Inject constructor(): MockDataSource {
    override suspend fun userLogin(userData: UserDataLoginInputModel): ResponseState<Boolean> {
        return ResponseState.Success(data = true)
    }

    override suspend fun getCarsList(): ResponseState<List<CarModel>> {
        return ResponseState.Success(data = listOf(
            CarModel("mra", 1,"https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg",  "Benz", "C", 2022, 1000, "LA"),
            CarModel("mra", 2, "https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg", "Benz", "E", 2020, 5000, "LA"),
            CarModel("mra", 3, "https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg", "BMW", "Z", 2021, 2000, "LA"),
            CarModel("mra", 4, "https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg", "BMW", "X314", 2019, 3300, "LA"),
            CarModel("mra", 5, "https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg", "Bugatti", "Lumbo", 2018, 3600, "LA"),
            CarModel("mra", 6, "https://media.zigcdn.com/media/model/2023/Feb/e-class_360x240.jpg", "KIA", "Sportage", 2029, 8000, "LA"),
        ))
    }

    override suspend fun bookingCar(bookingData: BookingCarInputModel): ResponseState<Boolean> {
        return ResponseState.Success(data = true)
    }

}