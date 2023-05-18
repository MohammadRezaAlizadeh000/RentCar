package com.mra.rentcar.presenter.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mra.rentcar.presenter.view.BaseFragment
import com.mra.rentcar.presenter.view.adapter.CarsRecyclerViewAdapter
import com.mra.rentcar.presenter.viewmodel.CarsListViewModel
import com.mra.rentcar.utils.CAR_DATA
import com.mra.rentcar.utils.repeatOnLifeCycle
import com.mra.rentcar.utils.showLogoutBtn
import com.mra.rentcar.utils.toast
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.CarsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsListFragment: BaseFragment<CarsFragmentBinding>(CarsFragmentBinding::inflate) {

    private val viewModel: CarsListViewModel by viewModels()
    private var carsRecyclerViewAdapter: CarsRecyclerViewAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLogoutBtn()

        initCarsRecyclerViewAdapter()

        initCarRecyclerView()

        getAllCars()

        observeAllCars()
    }

    private fun getAllCars() {
        viewModel.getAllCars()
    }

    private fun observeAllCars() {
        repeatOnLifeCycle(viewModel.carsResultFlow) { result ->
            result.data?.let {
                carsRecyclerViewAdapter?.submitData(it)
            }

            result.message?.let {
                toast(it)
                viewModel.clearMessage()
            }
        }
    }

    private fun initCarsRecyclerViewAdapter() {
        carsRecyclerViewAdapter = CarsRecyclerViewAdapter {
            findNavController().navigate(R.id.action_carsListFragment_to_bookingCarFragment, bundleOf(CAR_DATA to it))
        }
    }

    private fun initCarRecyclerView() {
        binding?.apply {
            carsRecyclerView.apply {
                adapter = carsRecyclerViewAdapter
                context?.let { mContext ->
                    layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }
}