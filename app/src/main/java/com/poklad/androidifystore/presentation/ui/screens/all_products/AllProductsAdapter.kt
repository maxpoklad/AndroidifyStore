package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poklad.androidifystore.databinding.ItemProductBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.presentation.ui.base.BaseAdapter

class AllProductsAdapter() :
    BaseAdapter<ProductItem>() {

    private val differCallback = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }
    override val differList = AsyncListDiffer(this, differCallback)
    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    inner class ProductsHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<ProductItem> {
        override fun bind(item: ProductItem) {
            binding.apply {
                titleTextView.text = item.title
                priceButton.text = item.price
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(productImageView)
                root.setOnClickListener {
                    onItemClickListener?.let { it(item) }
                }
            }
        }
    }
}