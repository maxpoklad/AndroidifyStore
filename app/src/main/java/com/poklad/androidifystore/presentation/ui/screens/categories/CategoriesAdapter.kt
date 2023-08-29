package com.poklad.androidifystore.presentation.ui.screens.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.poklad.androidifystore.data.remote.model.ProductCategoryResponse
import com.poklad.androidifystore.databinding.ItemCategoryBinding
import com.poklad.androidifystore.presentation.ui.base.BaseAdapter

//todo do mapping or not?
class CategoriesAdapter : BaseAdapter<ProductCategoryResponse>() {
    private val differCallback = object : DiffUtil.ItemCallback<ProductCategoryResponse>() {
        override fun areItemsTheSame(
            oldItem: ProductCategoryResponse,
            newItem: ProductCategoryResponse
        ): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(
            oldItem: ProductCategoryResponse,
            newItem: ProductCategoryResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
    override val differList: AsyncListDiffer<ProductCategoryResponse>
        get() = AsyncListDiffer(this, differCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    inner class CategoriesHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryName: String) {
            binding.apply {
                categoryNameTextView.text = categoryName
               /* root.setOnClickListener {
                    onItemClickListener?.let {
                        it(categoryName)
                    }
                }*/
            }
        }
    }

}