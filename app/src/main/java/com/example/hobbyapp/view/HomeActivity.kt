package com.example.hobbyapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
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

    companion object {
        fun logout(activity: Activity) {
            val shared = activity.packageName
            val sharedPref: SharedPreferences = activity.getSharedPreferences(shared, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.remove("KEY_ID")
            editor.apply()
        }

    }

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
        val toolbar = Toolbar(applicationContext)
        setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
        binding.bottomNav.setupWithNavController(navController)

        if (MainActivity.getSharedPref(this) == 0) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}