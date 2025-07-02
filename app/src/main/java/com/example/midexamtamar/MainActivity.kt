package com.example.midexamtamar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.midexamtamar.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavView = binding.bottomNav
        bottomNavView.setupWithNavController(navController)
        bottomNavView.setBackgroundColor(Color.TRANSPARENT)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment, R.id.resetPasswordFragment -> {
                    setBottomNavigationVisibility(View.GONE)
                }
                else -> {
                    setBottomNavigationVisibility(View.VISIBLE)
                }
            }
        }

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToMainScreen()
        } else {
            goToSignInScreen()
        }
    }


    private fun goToMainScreen() {
        if (navController.currentDestination?.id != R.id.homeFragment) {
            navController.navigate(R.id.homeFragment)
        }
    }

    private fun goToSignInScreen() {
        setBottomNavigationVisibility(View.GONE)
        if (navController.currentDestination?.id != R.id.loginFragment) {
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNav.visibility = visibility  // ან binding.bottomNavView
    }
}
