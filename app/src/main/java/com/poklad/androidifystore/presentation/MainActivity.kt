package com.poklad.androidifystore.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.poklad.androidifystore.R
import com.poklad.androidifystore.databinding.ActivityMainBinding
import com.poklad.androidifystore.presentation.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {
        override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
            return ActivityMainBinding.inflate(layoutInflater)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
//        setUpBottomNavigationMenu(navController)
//        setupActionBarWithNavController(navController)

    }

//    private fun setUpBottomNavigationMenu(navController: NavController) {
//        binding.bottomNavigationView.setupWithNavController(navController)
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}