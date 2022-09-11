package com.nazrawi.table.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nazrawi.table.R
import com.nazrawi.table.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        binding.mainActivityBottomNav.setupWithNavController(navController)

        setSupportActionBar(binding.mainActivityToolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.premierLeagueFragment,
                R.id.championshipFragment,
                R.id.laLigaFragment,
                R.id.bundesligaFragment,
                R.id.league1Fragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}