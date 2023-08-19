package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentAllProductsBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsFragment : BaseFragment<FragmentAllProductsBinding, BaseViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: AllProductsViewModel by viewModels() {
        viewModelFactory
    }
    private val allProductsAdapter: AllProductsAdapter by lazy {
        AllProductsAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this)
    }

    override fun getViewBinding(): FragmentAllProductsBinding =
        FragmentAllProductsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            binding?.apply {
                                progressBarAllProducts.visibility = View.GONE
                                renderList(resource.data)
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
    }

    private fun renderList(productsList: List<ProductItem>) {
        allProductsAdapter.list = productsList
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