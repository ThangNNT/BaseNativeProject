package com.example.nativebaseproject.base.activity

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.common.extension.createProgressDialog
import com.example.nativebaseproject.common.extension.hideKeyboard


abstract class BaseActivity<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB): AppCompatActivity() {
    var shouldHideKeyboardWhenTouchOutside = true
    private lateinit var mBinding: VB
    val binding: VB
        get() = mBinding

    private val progressDialog by lazy { createProgressDialog() }

    private lateinit var prevConfig: Configuration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prevConfig = Configuration(resources.configuration)
        mBinding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    abstract fun setup()

    fun showProgressDialog(){
        progressDialog.show()
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isNightConfigChanged(newConfig)) { // night mode has changed
            recreate()
        }
        prevConfig = Configuration(resources.configuration)
    }

    protected open fun isNightConfigChanged(newConfig: Configuration): Boolean {
        return newConfig.diff(prevConfig) and ActivityInfo.CONFIG_UI_MODE != 0 && isOnDarkMode(
            newConfig
        ) != isOnDarkMode(prevConfig)
    }

    open fun isOnDarkMode(configuration: Configuration): Boolean {
        return configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (shouldHideKeyboardWhenTouchOutside){
            val view: View? = currentFocus
            val ret = super.dispatchTouchEvent(event)
            if (view is EditText) {
                currentFocus?.let {
                    val w: View = it
                    val screenCoordinates = IntArray(2)
                    w.getLocationOnScreen(screenCoordinates)
                    val x: Float = event.rawX + w.left - screenCoordinates[0]
                    val y: Float = event.rawY + w.top - screenCoordinates[1]
                    if (event.action == MotionEvent.ACTION_UP
                        && (x < w.left || x >= w.right || y < w.top || y > w.bottom)
                    ) {
                        binding.root.hideKeyboard()
                    }
                }
            }
            return ret
        } else return super.dispatchTouchEvent(event)
    }


}