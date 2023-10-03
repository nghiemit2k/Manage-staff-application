package com.example.projectmanagestaff.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectmanagestaff.R
import com.example.projectmanagestaff.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // thiết lập bottom navigation
        val bottomNavigationView = binding.includeMainLayout?.bottomNav
        bottomNavigationView?.setupWithNavController(navController)

        // thiết lập nút Up và 3 màn hình chính của 3 tab và navigation drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_register, R.id.nav_settings),
            drawerLayout = binding.drawerLayout
        )
        // gắn navigation view vào navController:
        binding.navView?.setupWithNavController(navController)

        // thiết lập toolbar làm action bar
        setSupportActionBar(binding.includeMainLayout?.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.includeMainLayout
            ?.toolbar
            ?.setupWithNavController(navController, appBarConfiguration)

        onDestinationChanged()
        setUpDrawerListener()
        setUpNavigationDrawerItemSelected()
    }

    private fun onDestinationChanged() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> {
                    // do something if destination is home fragment
                }

                R.id.nav_register -> {
                    // do something if destination is register fragment
                }

                R.id.nav_settings -> {
                    // do something if destination is setting fragment
                }

                else -> {
                    // do something
                }
            }
        }
    }

    // bắt sự kiện navigation drawer mở, đóng, trượt, thay đổi trạng thái
    private fun setUpDrawerListener() {
        binding.drawerLayout?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // todo
            }

            override fun onDrawerOpened(drawerView: View) {
                // todo
            }

            override fun onDrawerClosed(drawerView: View) {
                // todo
            }

            override fun onDrawerStateChanged(newState: Int) {
                // todo
            }
        })
    }

    // lắng nghe sự kiện một phần tử trong menu navigation drawer được click
    private fun setUpNavigationDrawerItemSelected() {
        binding.navView?.setNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_item_drawer_search -> {
                    // todo
                }

                R.id.menu_item_drawer_about -> {
                    // todo
                }

                R.id.menu_item_drawer_import -> {
                    // todo
                }

                // ...
            }
            false
        }
    }

    // cho phép bắt sự kiến nhấn Back
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}