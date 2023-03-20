package com.example.nativebaseproject.base.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.activity.BaseActivity


fun BaseActivity<ViewBinding>.hideKeyboard(){
    val inputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
}

fun BaseActivity<ViewBinding>.showKeyboard(editText: EditText){
    editText.requestFocus()
    val imm =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(editText, 0)
}