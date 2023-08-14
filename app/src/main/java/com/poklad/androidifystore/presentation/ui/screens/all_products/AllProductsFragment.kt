package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.databinding.FragementAllProductsBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel

class AllProductsFragment(
) : BaseFragment<FragementAllProductsBinding, BaseViewModel>() {

    override val viewModel: AllProductsViewModel by viewModels()
    private val allProductsAdapter: AllProductsAdapter by lazy {// todo//and there's no abstraction to pass on?
        AllProductsAdapter()
    }

    override fun getViewBinding(): FragementAllProductsBinding =
        FragementAllProductsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.recycleViewProductList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allProductsAdapter
        }
        allProductsAdapter.setOnclickListener { item ->
            Toast.makeText(requireContext(), item.id.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}