package com.poklad.androidifystore.presentation.ui.screens.auth.logIn

import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.FragmentLogInBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment

class LogInFragment : BaseFragment<FragmentLogInBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentLogInBinding =
        FragmentLogInBinding.inflate(inflater)
}