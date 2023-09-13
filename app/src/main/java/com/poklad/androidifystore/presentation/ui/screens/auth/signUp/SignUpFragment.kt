package com.poklad.androidifystore.presentation.ui.screens.auth.signUp
import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.FragmentSignUpBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(inflater)
}
