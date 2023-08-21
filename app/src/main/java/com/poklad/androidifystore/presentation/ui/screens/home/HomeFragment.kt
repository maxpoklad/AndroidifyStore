package com.poklad.androidifystore.presentation.ui.screens.home

import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.FragmentHomeBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)

}