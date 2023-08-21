package com.poklad.androidifystore.presentation.ui.screens.product_details

import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.FragmentProductDetailsBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel

class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProductDetailsBinding {
        return FragmentProductDetailsBinding.inflate(inflater)
    }
}