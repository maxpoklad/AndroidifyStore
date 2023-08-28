package com.poklad.androidifystore.presentation.ui.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.poklad.androidifystore.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val sliderList: List<Int>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    override fun getCount(): Int {
        return sliderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.SliderViewHolder {
        val itemView: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.test_item, null)
        return SliderViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapter.SliderViewHolder, position: Int) {
        viewHolder.bind(sliderList[position])
    }

    class SliderViewHolder(itemView: View) : ViewHolder(itemView) {
        private val sliderIV: ImageView = itemView.findViewById(R.id.idIVSliderItem)
        fun bind(resId: Int) {
            sliderIV.setImageResource(resId)
        }
    }

}