package com.poklad.androidifystore.presentation.ui.screens.cart

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.poklad.androidifystore.databinding.FragemntCartBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel

class CartFragment : BaseFragment<FragemntCartBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): FragemntCartBinding =
        FragemntCartBinding.inflate(layoutInflater)
}