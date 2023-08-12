package com.poklad.androidifystore.presentation

import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.ActivityMainBinding
import com.poklad.androidifystore.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}