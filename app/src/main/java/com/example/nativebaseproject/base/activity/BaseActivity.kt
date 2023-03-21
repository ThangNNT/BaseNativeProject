package com.example.nativebaseproject.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.extension.hideKeyboard

abstract class BaseActivity<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB): AppCompatActivity() {
    var shouldHideKeyboardWhenTouchOutside = true
    private lateinit var mBinding: VB
    val binding: VB
        get() = mBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    abstract fun setup()

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
                        this.hideKeyboard()
                    }
                }
            }
            return ret
        } else return super.dispatchTouchEvent(event)
    }

}