package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poklad.androidifystore.databinding.ItemProductBinding
import com.poklad.androidifystore.domain.model.ProductItem

class AllProductsAdapter() :
    RecyclerView.Adapter<AllProductsAdapter.ProductsHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }
    val differList = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int = differList.currentList.size

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(differList.currentList[position])
    }

    private var onItemClickListener: ((ProductItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ProductItem) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductsHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductItem) {
            binding.apply {
                titleTextView.text = product.title
                priceButton.text = product.price
                Glide.with(itemView.context)
                    .load(product.image)
                    .into(productImageView)
                root.setOnClickListener {
                    onItemClickListener?.let { it(product) }
                }
            }
        }
    }
}