package com.mra.rentcar.presenter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mra.rentcar.model.CarModel
import com.mra.rentcar.presenter.view.adapter.holder.CarHolder
import com.mra.rentcar.utils.showWithGlide
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.CarsRecyclerViewRowBinding

class CarsRecyclerViewAdapter(
    private val onViewCarClickListener: (CarModel) -> Unit
) : RecyclerView.Adapter<CarHolder>() {

    private val dataList = mutableListOf<CarModel>()
    fun submitData(data: List<CarModel>) {
        dataList.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarHolder(
        CarsRecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        binding.root.setOnClickListener { onViewCarClickListener.invoke(dataList[layoutPosition]) }
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val element = dataList[position]
        with(holder.binding) {
            carImage.showWithGlide(element.carImage)// there is no image url in response
            make.text = root.context.getString(R.string.carMakeLabel).plus(element.make)
            model.text = root.context.getString(R.string.carModelLabel).plus(element.model)
            year.text = root.context.getString(R.string.carYearLabel).plus(element.year)
            location.text = root.context.getString(R.string.carLocationLabel).plus(element.location)
            price.text = root.context.getString(R.string.carPriceLabel).plus(element.price)
        }
    }

    override fun getItemCount() = dataList.size
}