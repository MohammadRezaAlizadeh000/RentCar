package com.mra.rentcar.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.rentcar.domin.GetCarsUseCase
import com.mra.rentcar.model.CarModel
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
class CarsListViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {

    private var job: Job? = null

    data class CarsResultFlowModel (
        val data: List<CarModel>? = null,
        val message: String? = null
    )

    interface DataBase<T> {
        val data: T
    }

    private val _carsResultFlow = MutableStateFlow(CarsResultFlowModel())
    val carsResultFlow: StateFlow<CarsResultFlowModel> = _carsResultFlow


    fun getAllCars() {
        job = viewModelScope.launch(Dispatchers.IO) {
            when(val response = getCarsUseCase()) {
                is ResponseState.Error -> _carsResultFlow.value = CarsResultFlowModel(message = response.message)
                is ResponseState.Success -> _carsResultFlow.value = CarsResultFlowModel(data = response.data)
                is ResponseState.Loading -> _carsResultFlow.value = CarsResultFlowModel()
            }
        }
    }


    fun clearMessage() {
        _carsResultFlow.update {
            it.copy(message = null)
        }
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
        job = null
    }
}