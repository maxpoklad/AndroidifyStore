package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.R
import com.poklad.androidifystore.databinding.FragementAllProductsBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.Resource
import kotlinx.coroutines.launch

class AllProductsFragment(
) : BaseFragment<FragementAllProductsBinding, BaseViewModel>() {

    override val viewModel: AllProductsViewModel by viewModels()
    private val allProductsAdapter: AllProductsAdapter by lazy {
        AllProductsAdapter()
    }

    override fun getViewBinding(): FragementAllProductsBinding =
        FragementAllProductsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().navigate(R.id.action_allProductsFragment_to_homeScreenFragment)
        initRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            viewModel.products.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding?.apply {
                            progressBarAllProducts.visibility = View.GONE
                            recycleViewProductList.visibility = View.VISIBLE
                        }
                    }

                    is Resource.Loading -> {
                        binding?.apply {
                            progressBarAllProducts.visibility = View.VISIBLE
                            recycleViewProductList.visibility = View.GONE
                        }
                    }

                    is Resource.Error -> {
                        binding?.progressBarAllProducts?.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            resource.throwable.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
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