package com.example.nativebaseproject

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.nativebaseproject.base.activity.BaseMVVMActivity
import com.example.nativebaseproject.common.extension.setSingleClickListener
import com.example.nativebaseproject.common.extension.shareSimpleText
import com.example.nativebaseproject.common.inapp_review.launchInAppReview
import com.example.nativebaseproject.common.util.changeLanguage
import com.example.nativebaseproject.common.util.getCurrentLanguageModel
import com.example.nativebaseproject.databinding.ActivityMainBinding
import com.example.nativebaseproject.ui.language.LanguageActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseMVVMActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawableToggle: ActionBarDrawerToggle

    private val onLanguageChanged = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            changeLanguage(getCurrentLanguageModel())
        }
    }

    override fun setup() {
        setSupportActionBar(binding.toolbar)
        setupDrawer()
        setupNavigation()
    }
    override fun setupObserver() {

    }
    private fun setupDrawer(){
        drawableToggle = ActionBarDrawerToggle(this, binding.drawerLayout,binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(drawableToggle)
        drawableToggle.isDrawerIndicatorEnabled = true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        drawableToggle.syncState()
        setupDrawerListener()
    }

    private fun setupDrawerListener(){
        binding.layoutDrawer.layoutLanguage.setSingleClickListener {
            onLanguageChanged.launch(LanguageActivity.newIntent(this))
        }
        binding.layoutDrawer.layoutReview.setOnClickListener {
            launchInAppReview(this, {}, {})
        }
        binding.layoutDrawer.layoutShareApp.setOnClickListener {
            val appShareContent =  "${getString(R.string.share_app_subject)} ${AppConfig.APP_STORE_LINK}"
            shareSimpleText(appShareContent, getString(R.string.app_name))
        }
    }

    private fun setupNavigation(){
        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            ),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.setOnItemSelectedListener {
//            NavigationUI.onNavDestinationSelected(it, navController)
//            navController.popBackStack(it.itemId, inclusive = false)
//            true
//        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications -> {
                    drawableToggle.isDrawerIndicatorEnabled = true
                    binding.navView.isVisible = true
                }
                else -> {
                    drawableToggle.isDrawerIndicatorEnabled = false
                    binding.navView.isVisible = false
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawableToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawableToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawableToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawers()
        }
        else super.onBackPressed()
    }
}