package com.vahitkeskin.appcentnasaapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vahitkeskin.appcentnasaapp.R
import com.vahitkeskin.appcentnasaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Kotlin made it mandatory to use ViewBinding in Android JetPack as of 1.4.20-M2.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Created to use fragments used with navigation in NavigationHosFragment
        //Fragment image replacement for each BottomNavigationView element selected
        val navController = findNavController(R.id.fragment)
        binding.bottomNavView.setupWithNavController(navController)

    }
}