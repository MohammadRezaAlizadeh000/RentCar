package com.mra.rentcar.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes message: Int) {
    context?.let {
        Toast.makeText(it, resources.getString(message), Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toast(message: String) {
    context?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}