package com.poklad.androidifystore.presentation.ui.screens.all_products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.databinding.FragementAllProductsBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsFragment(
) : BaseFragment<FragementAllProductsBinding, BaseViewModel>() {

    override val viewModel: AllProductsViewModel by viewModels()

    @Inject
    lateinit var adapter: AllProductsAdapter
    override fun getViewBinding(): FragementAllProductsBinding =
        FragementAllProductsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        binding.apply {
            lifecycleScope.launch {
                viewModel.allProductsLiveData.observe(viewLifecycleOwner) {
                    adapter.differList.submitList(it)
                    adapter.setOnItemClickListener {
                        Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val recycleView = binding?.recycleViewProductList
        recycleView?.layoutManager = LinearLayoutManager(requireActivity())
        recycleView?.adapter = adapter
    }
}