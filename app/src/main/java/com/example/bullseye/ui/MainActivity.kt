package com.example.bullseye.ui

import android.os.Bundle
import android.view.View.generateViewId
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.bullseye.R
import com.example.bullseye.Screen.getScreenSize
import com.example.bullseye.gameModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resources.getScreenSize()
        startKoin {
            androidContext(this@MainActivity)
            modules(gameModule)
        }

        // Create a FragmentContainerView dynamically
        val fragmentContainer = FragmentContainerView(this).apply {
            setBackgroundResource(R.drawable.background)
        }
        fragmentContainer.id = generateViewId()

        setContentView(fragmentContainer)

        // Create a NavHostFragment dynamically
        val navHostFragment = NavHostFragment.create(R.navigation.nav_graph)

        // Add the NavHostFragment to the FragmentContainerView
        supportFragmentManager.beginTransaction()
            .add(fragmentContainer.id, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
