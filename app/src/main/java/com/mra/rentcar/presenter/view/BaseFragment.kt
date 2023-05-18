package com.mra.rentcar.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(private val myLayoutInflater: (LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = myLayoutInflater(layoutInflater)

        return binding?.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}