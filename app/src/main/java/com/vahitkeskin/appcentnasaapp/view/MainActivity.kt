package com.vahitkeskin.appcentnasaapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vahitkeskin.appcentnasaapp.R
import com.vahitkeskin.appcentnasaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Kotlin made it mandatory to use ViewBinding in Android JetPack as of 1.4.20-M2.
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Created to use fragments used with navigation in NavigationHosFragment
        navController = Navigation.findNavController(this, R.id.fragment)

        //Fragment image replacement for each BottomNavigationView element selected
        binding.bottomNavView.setOnNavigationItemSelectedListener(bottomNavMethod)

    }

    private val bottomNavMethod: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.curiosityFragment -> {
                    navController.navigate(R.id.curiosityFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.opportunityFragment -> {
                    navController.navigate(R.id.opportunityFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.spiritFragment -> {
                    navController.navigate(R.id.spiritFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}