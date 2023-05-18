package com.mra.rentcar.presenter.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mra.rentcar.data.model.BookingCarInputModel
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.presenter.view.BaseFragment
import com.mra.rentcar.presenter.viewmodel.BookingCarViewModel
import com.mra.rentcar.utils.*
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.BookingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BookingCarFragment : BaseFragment<BookingFragmentBinding>(BookingFragmentBinding::inflate) {

    private val viewModel: BookingCarViewModel by viewModels()
    private var carData: CarModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carData = arguments?.getParcelable(CAR_DATA)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            carData?.let {
                carImageView.showWithGlide(it.carImage)// there is no image url in response
                make.text = root.context.getString(R.string.carMakeLabel).plus(" " + it.make)
                model.text = root.context.getString(R.string.carModelLabel).plus(" " + it.model)
                year.text = root.context.getString(R.string.carYearLabel).plus(" " + it.year)
                location.text =
                    root.context.getString(R.string.carLocationLabel).plus(" " + it.location)
                price.text = root.context.getString(R.string.carPriceLabel).plus(" " + it.price)
            }

            startDatePickerLayout.setEndIconOnClickListener {
                openDatePickerDialog(startDatePicker.text.toString()) { startDatePicker.setText(it) }
            }
            endDatePickerLayout.setEndIconOnClickListener {
                openDatePickerDialog(endDatePicker.text.toString()) { endDatePicker.setText(it) }
            }

            startDatePicker.onTextChangeListener { if (it.isNotEmpty()) startDatePickerLayout.removeError() else startDatePickerLayout.removeError() }
            endDatePicker.onTextChangeListener { if (it.isNotEmpty()) endDatePickerLayout.removeError() else endDatePickerLayout.removeError() }

            backBtn.setOnClickListener { findNavController().popBackStack() }

            confirmBtn.setOnClickListener { confirmBtnClickListener() }
        }

        observeBookingResult()
    }

    private fun openDatePickerDialog(selectedTime: String? = null, result: (String) -> Unit) {
        if (null == selectedTime || selectedTime.isEmpty())
            findNavController().navigate(R.id.action_bookingCarFragment_to_dateTimePickerDialog)
        else
            findNavController().navigate(
                R.id.action_bookingCarFragment_to_dateTimePickerDialog, bundleOf(
                    SELECTED_TIME to selectedTime
                )
            )
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            SELECTED_TIME
        )?.observe(viewLifecycleOwner) { result.invoke(it) }
    }

    private fun confirmBtnClickListener() {
        binding?.apply {
            val startDate = startDatePicker.text.toString()
            val endDate = endDatePicker.text.toString()

            checkValidation()

            if (startDate.isNotEmpty() && endDate.isNotEmpty())
                bookingCarRequest(createBookingDataModel(startDate, endDate))
        }
    }

    private fun checkValidation() {
        binding?.apply {
            val startDate = startDatePicker.text.toString()
            val endDate = endDatePicker.text.toString()

            if (endDate.isNotEmpty()) endDatePickerLayout.removeError()
            else endDatePickerLayout.callError(resources.getString(R.string.fieldEmpty))

            if (startDate.isNotEmpty()) startDatePickerLayout.removeError()
            else startDatePickerLayout.callError(resources.getString(R.string.fieldEmpty))
        }
    }

    private fun createBookingDataModel(
        startDate: String,
        endDate: String
    ): BookingCarInputModel {
        return BookingCarInputModel(
            carId = carData?.carId,
            username = carData?.username,
            startDate = startDate,
            endDate = endDate
        )
    }

    private fun bookingCarRequest(bookingCarInputModel: BookingCarInputModel) {
        viewModel.bookingCar(bookingCarInputModel)
    }

    private fun observeBookingResult() {
        repeatOnLifeCycle(viewModel.bookingResultFlow) { result ->
            result.data?.let {
                if (it)
                    toast(R.string.successBooking)
            }

            result.message?.let {
                toast(it)
                viewModel.clearMessage()
            }
        }
    }
}