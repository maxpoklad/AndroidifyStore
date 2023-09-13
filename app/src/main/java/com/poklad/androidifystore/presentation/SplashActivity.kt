package com.poklad.androidifystore.presentation

import android.view.LayoutInflater
import com.poklad.androidifystore.databinding.ActivitySplashBinding
import com.poklad.androidifystore.presentation.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(inflater)
    }
}