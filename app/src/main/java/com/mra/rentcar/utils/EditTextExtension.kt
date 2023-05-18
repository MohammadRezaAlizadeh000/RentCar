package com.mra.rentcar.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun EditText.onTextChangeListener(onListen: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onListen.invoke(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })
}

fun TextInputLayout.callError(error: String) {
    this.error = error
    isErrorEnabled = true
}

fun TextInputLayout.removeError() {
    this.error = ""
    isErrorEnabled = false
}