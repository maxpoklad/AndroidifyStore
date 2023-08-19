package com.poklad.androidifystore.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.poklad.androidifystore.R
import com.poklad.androidifystore.databinding.ActivityMainBinding
import com.poklad.androidifystore.presentation.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val navController = findNavController(R.id.fragmentContainer)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}