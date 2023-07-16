package com.poklad.androidifystore.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!
    protected abstract val viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        /*
                viewModel = ViewModelProvider(this)[getViewModelClass()]
        */
        return _binding?.root
    }

    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    /*
        abstract fun getViewModelClass(): Class<VM>
    */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}