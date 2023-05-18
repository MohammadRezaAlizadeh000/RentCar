package com.mra.rentcar.presenter.view.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.mra.rentcar.utils.SELECTED_TIME
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.DateTimePickerDialogBinding

class DateTimePickerDialog : DialogFragment() {

    private var binding: DateTimePickerDialogBinding? = null
    private var selectedDate: String? = null
    private var selectedTime: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val data = arguments?.getString(SELECTED_TIME)?.replace(" ", "")?.split("-")
        val date = data?.get(0)?.split("/")
        val time = data?.get(1)?.split(":")


        binding = DateTimePickerDialogBinding.inflate(layoutInflater).apply {

            date?.let { datePicker.updateDate(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
            time?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    timePicker.hour = time[0].toInt()
                    timePicker.minute = time[1].toInt()
                } else {
                    timePicker.currentHour = time[0].toInt()
                    timePicker.currentMinute = time[1].toInt()
                }
            }

            okBtn.setOnClickListener {
                if (datePicker.visibility == View.VISIBLE) {
                    hideDatePicker()
                    showTimePicker()
                    toBackBtn()
                } else {
                    selectedDate = getSelectedDate()
                    selectedTime = getSelectedTime()
                    dismiss()
                }
            }

            cancelBtn.setOnClickListener {
                if (datePicker.visibility == View.VISIBLE) {
                    dismiss()
                } else {
                    hideTimePicker()
                    showDatePicker()
                    toCancelBtn()
                }
            }
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding?.root)
            .create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    private fun getSelectedDate(): String {
        return "${binding?.datePicker?.year}/${binding?.datePicker?.month}/${binding?.datePicker?.dayOfMonth}"
    }

    private fun getSelectedTime(): String {
        return "${binding?.timePicker?.currentHour}:${binding?.timePicker?.currentMinute}"
    }

    private fun hideDatePicker() {
        binding?.datePicker?.visibility = View.GONE
    }

    private fun showTimePicker() {
        binding?.timePicker?.visibility = View.VISIBLE
    }

    private fun hideTimePicker() {
        binding?.timePicker?.visibility = View.GONE
    }

    private fun showDatePicker() {
        binding?.datePicker?.visibility = View.VISIBLE
    }

    private fun toBackBtn() {
        binding?.cancelBtn?.text = getString(R.string.back)
    }

    private fun toCancelBtn() {
        binding?.cancelBtn?.text = getString(R.string.cancel)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (null != selectedDate && null != selectedTime)
            findNavController().currentBackStackEntry?.savedStateHandle?.set(
                SELECTED_TIME,
                "$selectedDate - $selectedTime"
            )
        super.onDismiss(dialog)
    }

}