package com.example.hobbyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = (supportFragmentManager.findFragmentById(R.id.navHome) as NavHostFragment).navController
        val appBarConfig = AppBarConfiguration(setOf(
            R.id.itemHome,
            R.id.itemHistory,
            R.id.itemProfile
        ))

//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
        binding.bottomNav.setupWithNavController(navController)
    }
}