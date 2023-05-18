package com.mra.rentcar.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.domin.BookingCarUseCase
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
class BookingCarViewModel @Inject constructor(
    private val bookingCarUseCase: BookingCarUseCase
): ViewModel() {

    private var job: Job? = null

    data class BookingResultFlowModel (
        val data: Boolean? = null,
        val message: String? = null
    )

    private val _bookingResultFlow = MutableStateFlow(BookingResultFlowModel())
    val bookingResultFlow: StateFlow<BookingResultFlowModel> = _bookingResultFlow


    fun bookingCar(bookingCarInputModel: BookingCarInputModel) {
        job = viewModelScope.launch(Dispatchers.IO) {
            when(val response = bookingCarUseCase(bookingCarInputModel)) {
                is ResponseState.Error -> _bookingResultFlow.value = BookingResultFlowModel(message = response.message)
                is ResponseState.Success -> _bookingResultFlow.value = BookingResultFlowModel(data = response.data)
                is ResponseState.Loading -> _bookingResultFlow.value = BookingResultFlowModel()
            }
        }
    }


    fun clearMessage() {
        _bookingResultFlow.update {
            it.copy(message = null)
        }
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
        job = null
    }

}