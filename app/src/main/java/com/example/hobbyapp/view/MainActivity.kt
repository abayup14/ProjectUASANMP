package com.example.hobbyapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        fun getSharedPref(activity: Activity): Int {
            val shared = activity.packageName
            val sharedPref: SharedPreferences = activity.getSharedPreferences(shared, Context.MODE_PRIVATE)
            val res = sharedPref.getInt("KEY_ID", 0)
            Log.d("cek", res.toString())

            return res
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController)

        if (getSharedPref(this) != 0) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}