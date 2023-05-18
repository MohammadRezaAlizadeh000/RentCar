package com.mra.rentcar.utils

import androidx.fragment.app.Fragment
import com.mra.rentcar.presenter.view.MainActivity

// this is a class to give easy access to main activity public function from the fragments

fun Fragment.hideLogoutBtn() {
    activity?.let { (it as MainActivity).hideLogoutBtn() }
}

fun Fragment.showLogoutBtn() {
    activity?.let { (it as MainActivity).showLogoutBtn() }
}