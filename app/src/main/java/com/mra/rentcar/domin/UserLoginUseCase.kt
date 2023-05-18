package com.mra.rentcar.domin

import com.mra.rentcar.data.Repository
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.utils.ResponseState
import javax.inject.Inject

interface UserLoginUseCase {
    suspend operator fun invoke(userDataLoginInputModel: UserDataLoginInputModel): ResponseState<Boolean>
}

class UserLoginUseCaseImpl @Inject constructor(
    private val repository: Repository
): UserLoginUseCase {

    override suspend fun invoke(userDataLoginInputModel: UserDataLoginInputModel): ResponseState<Boolean> {
        return repository.userLogin(userDataLoginInputModel)
    }

}