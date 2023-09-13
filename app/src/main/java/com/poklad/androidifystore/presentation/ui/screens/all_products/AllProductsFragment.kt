package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentAllProductsBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.mapper.ProductItemToProductItemUi
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.presentation.ui.screens.product_details.ProductDetailsFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsFragment : BaseFragment<FragmentAllProductsBinding, BaseViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: AllProductsViewModel by viewModels {
        viewModelFactory
    }
    private val allProductsAdapter: AllProductsAdapter by lazy {
        AllProductsAdapter()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentAllProductsBinding =
        FragmentAllProductsBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpObserver()
    }
    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingFlow.collect { showLoader ->
                    if (showLoader) {
                        binding.apply {
                            progressBarAllProducts.visible()
                            recycleViewProductList.invisible()
                        }
                    } else {
                        binding.apply {
                            progressBarAllProducts.invisible()
                            recycleViewProductList.visible()
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect { productsList ->
                renderList(productsList)
            }
        }
    }

    private fun renderList(productsList: List<ProductItem>) {
        allProductsAdapter.list = productsList
    }

    private fun initRecyclerView() {
        setUpRecyclerView(
            allProductsAdapter,
            binding.recycleViewProductList,
            LinearLayoutManager.VERTICAL,
        ) { productItem ->
            val product = ProductItemToProductItemUi().map(productItem)
            navigateToFragment(
                R.id.action_allProductsFragment_to_productDetailsFragment,
                bundleOf(ProductDetailsFragment.ARG_PRODUCT to product)
            )
        }
    }
}