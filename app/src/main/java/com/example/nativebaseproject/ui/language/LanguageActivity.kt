package com.example.nativebaseproject.ui.language

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nativebaseproject.AppConfig
import com.example.nativebaseproject.MainActivity
import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.activity.BaseMVVMActivity
import com.example.nativebaseproject.common.util.changeLanguage
import com.example.nativebaseproject.common.util.getCurrentLanguageModel
import com.example.nativebaseproject.data.local.AppDataStore
import com.example.nativebaseproject.databinding.ActivityLanguageBinding
import timber.log.Timber
import java.util.Locale


/**
 * Created by ThangNNT on 23/03/2023.
 */
class LanguageActivity : BaseMVVMActivity<ActivityLanguageBinding>(ActivityLanguageBinding::inflate) {
    override val viewModel: LanguageViewModel by viewModels()

    private val isOpenApp by lazy {
        intent.getBooleanExtra(IS_OPEN_APP, true)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        if (isOpenApp) installSplashScreen()
        else setTheme(R.style.Theme_LanguageActivity)
        super.onCreate(savedInstanceState)
    }

    override fun setupObserver() {

    }

    override fun setup() {
        supportActionBar?.title = getString(R.string.language)
        if (isOpenApp) setupSplashScreen()

        if (viewModel.isFirstCreate){
            val currentLanguageModel = getCurrentLanguageModel()
            viewModel.currentLanguageModel = currentLanguageModel
            viewModel.isFirstCreate = false
        }
        binding.rvLanguages.apply {
            adapter = LanguageAdapter(AppConfig.appLanguages, selectedLanguage = viewModel.currentLanguageModel ){
                viewModel.currentLanguageModel = it
            }
            layoutManager = LinearLayoutManager(this@LanguageActivity, LinearLayoutManager.VERTICAL, false)
        }
        val language = Locale.getDefault().language
        Timber.d(language)
    }

    private fun setupSplashScreen(){
        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (viewModel.shouldHideSplash) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        onSplashScreenFinish()
                        true
                    } else {
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
    }

    private fun onSplashScreenFinish(){
        if (!AppDataStore.isFirstAppOpen){
            changeLanguage(getCurrentLanguageModel())
            goToMain()
        }
    }

    private fun goToMain(){
        //go to main activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.language_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btn_confirm_language){
            AppDataStore.isFirstAppOpen = false
            AppDataStore.currentLanguage = viewModel.currentLanguageModel.languageCode
            if (isOpenApp) {
                if (viewModel.currentLanguageModel.languageCode != Locale.getDefault().language){
                    changeLanguage(viewModel.currentLanguageModel)
                }
                goToMain()
            }
            else {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val IS_OPEN_APP = "IS_NOT_OPEN_APP"
        fun newIntent(context: Context, isOpenApp: Boolean = false) = Intent(context, LanguageActivity::class.java).apply {
            putExtra(IS_OPEN_APP, isOpenApp)
        }
    }
}