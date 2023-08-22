package com.poklad.androidifystore.presentation.ui.screens.product_details

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentProductDetailsBinding
import com.poklad.androidifystore.presentation.model.ProductItemUi
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.utils.log
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
        val product =
            requireArguments().getParcelable<ProductItemUi>(ARG_PRODUCT)
        log(product?.description.toString())
        binding.apply {
            titleTextView.text = product?.title
            priceTextView.text = product?.price
            descriptionTextView.text = product?.description
            Glide.with(this@ProductDetailsFragment)
                 .load(product?.image)
                 .into(productImageView)
        }
    }

    companion object {
        const val ARG_PRODUCT = "product"
    }
}