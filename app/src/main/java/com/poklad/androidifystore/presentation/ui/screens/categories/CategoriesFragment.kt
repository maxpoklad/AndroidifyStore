package com.poklad.androidifystore.presentation.ui.screens.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentCategoriesBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.invisible
import com.poklad.androidifystore.utils.log
import com.poklad.androidifystore.utils.toast
import com.poklad.androidifystore.utils.visible
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
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
                viewModel.categories.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.apply {
                                progressBarCategories.visible()
                                recycleViewCategories.invisible()
                            }
                        }

                        is Resource.Success -> {
                            binding.apply {
                                progressBarCategories.invisible()
                                renderList(resource.data)
                                recycleViewCategories.visible()
                            }
                        }

                        is Resource.Error -> {
                            binding.progressBarCategories.visible()
                            log(resource.throwable.toString())
                            Toast.makeText(
                                binding.root.context,
                                resource.throwable.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(categoriesName: List<String>) {
        categoriesAdapter.list = categoriesName
    }

    private fun initRecyclerView() {
        binding.recycleViewCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoriesAdapter
        }
        categoriesAdapter.setOnclickListener { category ->
            requireContext().toast(category)
        }
    }
}

