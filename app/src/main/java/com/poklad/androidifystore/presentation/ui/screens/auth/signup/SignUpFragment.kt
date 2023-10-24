package com.poklad.androidifystore.presentation.ui.screens.auth.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.data.firebase.User
import com.poklad.androidifystore.databinding.FragmentSignUpBinding
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.screens.auth.utils.AllEvents
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: SignUpViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@SignUpFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        listenFlow()
        registerUser()
    }

    private fun registerUser() {
        binding.apply {
            logInButton.setOnClickListener {
                progressBarSignin.visible()
                val emailText = editTextLogin.text.toString()
                val passwordText = editTextPassword.text.toString()
                val confirmPass = editTextConfirmPassword.text.toString()
                val user = User(emailText, passwordText)
                viewModel.signUpUser(user, confirmPass)
            }
            goToLoginTextView.setOnClickListener {
                navigateToFragment(R.id.action_signUpFragment_to_logInFragment, null)
            }
        }
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentUser.collect { user ->
                    user?.let {
                        navigateToFragment(R.id.action_signUpFragment_to_homeFragment2, null)
                    }
                }
            }
        }
    }

    private fun listenFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventsFlow.collect { event ->
                    when (event) {
                        is AllEvents.Error -> {
                            binding.apply {
                                Snackbar.make(requireView(), event.error, Snackbar.LENGTH_LONG)
                                    .show()
                                progressBarSignin.invisible()
                            }
                        }

                        is AllEvents.Message -> {
                            Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        }

                        is AllEvents.ErrorCode -> {
                            if (event.code == 1)
                                    binding.apply {
                                        textInputLogin.error = "email should not be empty"
                                        progressBarSignin.invisible()
                                }


                            if (event.code == 2)
                                binding.apply {
                                    textInputPassword.error = "password should not be empty"
                                    progressBarSignin.invisible()
                                }

                            if (event.code == 3)
                                binding.apply {
                                    textInputConfirmPassword.error = "passwords do not match"
                                    progressBarSignin.invisible()
                                }
                        }
                    }
                }
            }
        }
    }
}
