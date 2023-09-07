package com.poklad.androidifystore.presentation.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.poklad.androidifystore.databinding.ItemSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val sliderList: List<Int>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    override fun getCount(): Int {
        return sliderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val binding =
            ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.bind(sliderList[position])
    }

    class SliderViewHolder(private val binding: ItemSliderBinding) : ViewHolder(binding.root) {
        fun bind(resId: Int) {
            binding.imageViewSlider.setImageResource(resId)
        }
    }

}