package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentAllProductsBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.log
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductTest : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: AllProductsViewModel by viewModels() {
        viewModelFactory
    }
    private val allProductsAdapter: AllProductsAdapter by lazy {
        AllProductsAdapter()
    }
    private lateinit var binding: FragmentAllProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        StoreApp.daggerComponent.inject(this@AllProductTest)
        super.onCreate(savedInstanceState)
        binding = FragmentAllProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                                log("FR Resource.Success - ${resource.data}")
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
                                this@AllProductTest,
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
        log("renderList1 FR - $productsList")
        allProductsAdapter.list = productsList
        log("renderList2  FR- $productsList")
    }

    private fun initRecyclerView() {
        binding?.recycleViewProductList?.apply {
            layoutManager = LinearLayoutManager(this@AllProductTest)
            adapter = allProductsAdapter
        }
        allProductsAdapter.setOnclickListener { item ->
            Toast.makeText(this@AllProductTest, item.id.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}