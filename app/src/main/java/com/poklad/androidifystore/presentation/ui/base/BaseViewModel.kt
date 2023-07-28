package com.poklad.androidifystore.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.poklad.androidifystore.utils.DispatchersProvider

abstract class BaseViewModel(
    val dispatchers: DispatchersProvider
) : ViewModel() {
}