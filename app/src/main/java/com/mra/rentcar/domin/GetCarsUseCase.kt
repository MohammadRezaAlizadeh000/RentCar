package com.mra.rentcar.domin

import com.mra.rentcar.data.Repository
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.utils.ResponseState
import javax.inject.Inject

interface GetCarsUseCase {
    suspend operator fun invoke(): ResponseState<List<CarModel>>
}

class GetCarsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetCarsUseCase {
    override suspend fun invoke(): ResponseState<List<CarModel>> {
        return repository.getCarsList()
    }

}