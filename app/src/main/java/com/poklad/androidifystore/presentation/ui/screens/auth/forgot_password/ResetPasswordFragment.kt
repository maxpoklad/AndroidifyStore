package com.poklad.androidifystore.presentation.ui.screens.auth.forgot_password

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentResetPasswordBinding
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.toast
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.presentation.ui.screens.auth.utils.AllEvents
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: ResetPasswordViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentResetPasswordBinding =
        FragmentResetPasswordBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@ResetPasswordFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToFLow()
        sendPassword()
    }

    private fun listenToFLow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventsFlow.collect { event ->
                when (event) {
                    is AllEvents.Message -> {
                        requireContext().toast(event.message)
                        findNavController().navigate(R.id.action_forgotPasswordFragment_to_logInFragment)
                    }

                    is AllEvents.Error -> {
                        binding.apply {
                            resetPassProgressBar.invisible()
                            Snackbar.make(requireView(), event.error, Snackbar.LENGTH_LONG).show()
                        }
                    }

                    is AllEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding.apply {
                                textInputLogin.error = "email should not be empty!"
                                resetPassProgressBar.invisible()
                            }
                    }
                }

            }
        }
    }

    private fun sendPassword() {
        binding.apply {
            logInButton.setOnClickListener {
                resetPassProgressBar.visible()
                val email = editTextLogin.text.toString()
                viewModel.verifySendPasswordReset(email)
            }
        }
    }

}