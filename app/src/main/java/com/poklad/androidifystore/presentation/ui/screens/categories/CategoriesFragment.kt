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
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentCategoriesBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.Resource
import com.poklad.androidifystore.utils.invisible
import com.poklad.androidifystore.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: CategoriesViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@CategoriesFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.apply {
                                progressBarCategories.visible()
                            }
                        }

                        is Resource.Success -> {
                            binding.apply {
                                progressBarCategories.invisible()

                            }
                        }

                        is Resource.Error -> {
                            binding.progressBarCategories.visible()
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
}

