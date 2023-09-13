package com.poklad.androidifystore.presentation.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected abstract val differList: AsyncListDiffer<T>
    var list: List<T>
        get() = differList.currentList
        set(value) = differList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list.getOrNull(position)
        if (item != null) {
            (holder as Binder<T>).bind(item)
        }
    }

    protected var onItemClickListener: ((T) -> Unit)? = null

    fun setOnclickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }

    interface Binder<in T> {
        fun bind(item: T)
    }
}