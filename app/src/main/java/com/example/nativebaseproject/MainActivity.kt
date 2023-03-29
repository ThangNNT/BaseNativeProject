package com.example.nativebaseproject

import android.app.Activity
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
        viewModel.toolbarTitle.observe(this){
            supportActionBar?.title = it
        }
    }
    private fun setupDrawer(){
        setupDrawerListener()
    }

    private fun setupDrawerListener(){
        binding.layoutDrawer.apply {
            layoutTheme.setSingleClickListener {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                navController.navigate(R.id.action_drawer_to_theme)
            }
            layoutLanguage.setSingleClickListener {
                onLanguageChanged.launch(LanguageActivity.newIntent(this@MainActivity))
            }
            layoutReview.setSingleClickListener {
                launchInAppReview(this@MainActivity, {}, {})
            }
            layoutShareApp.setSingleClickListener {
                val appShareContent =  "${getString(R.string.share_app_subject)} ${AppConfig.APP_STORE_LINK}"
                shareSimpleText(appShareContent, getString(R.string.app_name))
            }
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
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.setOnItemSelectedListener {
//            NavigationUI.onNavDestinationSelected(it, navController)
//            navController.popBackStack(it.itemId, inclusive = false)
//            true
//        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications -> {
                    binding.navView.isVisible = true
                }
                else -> {
                    binding.navView.isVisible = false
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawers()
        }
        else super.onBackPressed()
    }
}