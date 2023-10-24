package com.poklad.androidifystore.presentation.ui.screens.auth.logIn

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
import com.poklad.androidifystore.databinding.FragmentLogInBinding
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.screens.auth.utils.AllEvents
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInFragment : BaseFragment<FragmentLogInBinding, LoginViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentLogInBinding =
        FragmentLogInBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@LogInFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        listenToChannels()
        logInApp()
    }

    private fun logInApp() {
        binding.apply {
            logInButton.setOnClickListener {
                progressBarLogIn.visible()
                val email = editTextLogin.text.toString()
                val password = editTextPassword.text.toString()
                val user = User(email, password)
                viewModel.logInUserValidate(user)
            }

            signupTextView.setOnClickListener {
                navigateToFragment(R.id.action_logInFragment_to_signUpFragment, null)
            }

            forgotPassTextView.setOnClickListener {
                navigateToFragment(R.id.action_logInFragment_to_forgotPasswordFragment, null)
            }
        }
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentUser.collect {
                    it?.let {
                        navigateToFragment(R.id.action_logInFragment_to_homeFragment2, null)
                    }
                }
            }
        }
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventsFlow.collect { event ->
                when (event) {
                    is AllEvents.Error -> {
                        binding.apply {
                            Snackbar.make(requireView(), event.error, Snackbar.LENGTH_LONG)
                                .show()
                            progressBarLogIn.invisible()
                        }
                    }

                    is AllEvents.Message -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                    }

                    is AllEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding.apply {
                                textInputLogin.error = "email should not be empty"
                                progressBarLogIn.invisible()
                            }

                        if (event.code == 2)
                            binding.apply {
                                textInputPassword.error = "password should not be empty"
                                progressBarLogIn.invisible()
                            }
                    }
                }
            }
        }
    }
}