package com.poklad.androidifystore.presentation.ui.screens.home

import androidx.fragment.app.viewModels
import com.poklad.androidifystore.databinding.FragmentHomeBinding
import com.poklad.androidifystore.presentation.ui.base.BaseFragment

class HomeScreenFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
}