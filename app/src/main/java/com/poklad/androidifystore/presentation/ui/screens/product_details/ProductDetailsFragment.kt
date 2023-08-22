package com.poklad.androidifystore.presentation.ui.screens.product_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentProductDetailsBinding
import com.poklad.androidifystore.presentation.model.ProductItemUi
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: ProductDetailsViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProductDetailsBinding {
        return FragmentProductDetailsBinding.inflate(inflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@ProductDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val productItemUi:ProductItemUi = requireArguments().getParcelable(ARG_PRODUCT,)
//        binding.apply {
//            titleTextView.text = productItem.title
//            descriptionTextView.text = productItem.description
//            priceTextView.text = productItem.price
//        }
    }

    companion object {
        const val ARG_PRODUCT = "product"
    }
}