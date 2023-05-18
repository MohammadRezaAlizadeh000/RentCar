package com.mra.rentcar.data.model.mapper

import com.mra.rentcar.data.model.BaseMapper
import com.mra.rentcar.data.model.CarResponseModel
import com.mra.rentcar.model.CarModel

object CarsListResponseMapper : BaseMapper<List<CarResponseModel>, List<CarModel>> {
    override fun mapTo(data: List<CarResponseModel>?): List<CarModel> {
        return data?.map {
            CarModel(
                it.username,
                it.carId,
                null,
                it.make,
                it.model,
                it.year,
                it.price,
                it.location
            )
        }
            ?: emptyList()
    }
}