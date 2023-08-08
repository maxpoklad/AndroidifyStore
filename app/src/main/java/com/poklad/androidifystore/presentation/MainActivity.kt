package com.poklad.androidifystore.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.poklad.androidifystore.R
import com.poklad.androidifystore.databinding.ActivityMainBinding
import com.poklad.androidifystore.presentation.ui.base.BaseActivity
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, AllProductsFragment())
            fragmentTransaction.commit()
        }
    }
}