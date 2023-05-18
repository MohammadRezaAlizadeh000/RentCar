package com.mra.rentcar.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.domin.UserLoginUseCase
import com.mra.rentcar.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase
): ViewModel() {

    private var job: Job? = null

    data class UserLoginResultFlowModel (
        val data: Boolean? = null,
        val message: String? = null
    )

    private val _userLoginResultFlow = MutableStateFlow(UserLoginResultFlowModel())
    val userLoginResultFlow: StateFlow<UserLoginResultFlowModel> = _userLoginResultFlow


    fun userLogin(userDataLoginInputModel: UserDataLoginInputModel) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when(val response = userLoginUseCase(userDataLoginInputModel)) {
                is ResponseState.Error -> _userLoginResultFlow.value = UserLoginResultFlowModel(message = response.message)
                is ResponseState.Success -> _userLoginResultFlow.value = UserLoginResultFlowModel(data = true)
                is ResponseState.Loading -> _userLoginResultFlow.value = UserLoginResultFlowModel()
            }
        }
    }


    fun clearUserLoginFlow() {
        _userLoginResultFlow.update {
            it.copy(data = null, message = null)
        }
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
        job = null
    }

}