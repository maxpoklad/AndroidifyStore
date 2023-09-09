package com.poklad.androidifystore.presentation.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.poklad.androidifystore.databinding.FragmentSplashBinding
import com.poklad.androidifystore.presentation.MainActivity
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: SplashViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSplashBinding =
        FragmentSplashBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            launchMainScreen()
        }
    }

    private fun launchMainScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
