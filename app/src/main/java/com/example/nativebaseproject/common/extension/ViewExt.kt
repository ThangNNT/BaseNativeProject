package com.example.nativebaseproject.common.extension

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by ThangNNT on 21/03/2023.
 */

/**
 * avoid user double click quickly
 */
fun View.setSingleClickListener(debounceTime: Long = 800, onClick: (View) -> Unit){
    var mLastClickTime: Long = 0
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - mLastClickTime < debounceTime){
            return@setOnClickListener
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        onClick.invoke(it)
    }
}

fun View.hideKeyboard(){
    val inputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard(editText: EditText){
    editText.requestFocus()
    val imm =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(editText, 0)
}