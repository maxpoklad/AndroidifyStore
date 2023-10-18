package com.poklad.androidifystore.presentation.ui.screens.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentCategoriesBinding
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.toast
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: CategoriesViewModel by viewModels {
        viewModelFactory
    }
    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@CategoriesFragment)
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
                            progressBarCategories.visible()
                            recycleViewCategories.invisible()
                        }
                    } else {
                        binding.apply {
                            progressBarCategories.invisible()
                            recycleViewCategories.visible()
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categories.collect { categoriesList ->
                renderList(categoriesList)
            }
        }
    }

    private fun renderList(categoriesName: List<String>) {
        categoriesAdapter.list = categoriesName
    }

    private fun initRecyclerView() {
        binding.recycleViewCategories.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        categoriesAdapter.setOnclickListener { category ->
            if (category == "all products") {
                findNavController().navigate(R.id.action_categoriesFragment_to_allProductsFragment)
            } else {
                requireContext().toast(category)
            }
        }
    }
}

