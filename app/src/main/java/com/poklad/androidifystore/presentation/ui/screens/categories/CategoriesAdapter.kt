package com.poklad.androidifystore.presentation.ui.screens.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.poklad.androidifystore.databinding.ItemCategoryBinding
import com.poklad.androidifystore.presentation.ui.base.BaseAdapter

class CategoriesAdapter : BaseAdapter<String>() {
    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
    override val differList: AsyncListDiffer<String> = AsyncListDiffer(this, differCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    inner class CategoriesHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<String> {
        override fun bind(item: String) {
            binding.apply {
                categoryNameTextView.text = item
                root.setOnClickListener {
                    onItemClickListener?.let { it(item) }
                }
            }
        }
    }
}