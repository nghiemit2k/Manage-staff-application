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
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var drawerLayout: DrawerLayout? = null
    private var navView: NavigationView? = null
    private var navigationRail: NavigationRailView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.include.toolbar
        val bottomNavigation = binding.include.bottomNav

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        // init bottom
        bottomNavigation?.setupWithNavController(navController)
        // lấy tham chiếu tới drawer layout nếu nó tồn tại
        drawerLayout = findViewById(R.id.drawer_layout)
        // thiết lập nút Up và 3 màn hình chính của 3 tab và navigation drawer
        // create function for press up
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.registerFragment, R.id.settingsFragment2),
            //
            drawerLayout = binding.drawerLayout
        )


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setupWithNavController(navController, appBarConfiguration)

        // thiết lập navigation rail nếu nó tồn tại
//        navigationRail = findViewById(R.id.nav_rail)
        navigationRail?.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
        setUpDrawerListener(drawerLayout)
        onDestinationChanged()
    }

    // lắng nghe sự kiện trang đích thay đổi
    private fun onDestinationChanged() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    // do something if destination is home fragment
                }

                R.id.registerFragment -> {
                    // do something if destination is register fragment
                }

                R.id.settingsFragment2 -> {
                    // do something if destination is setting fragment
                }

                else -> {
                    // do something
                }
            }
        }
    }

    // bắt sự kiện navigation drawer mở, đóng, trượt, thay đổi trạng thái
    private fun setUpDrawerListener(drawerLayout: DrawerLayout?) {
        drawerLayout?.addDrawerListener(object : DrawerLayout.DrawerListener {
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
    // create function for press back
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)  || super.onSupportNavigateUp()
    }


}