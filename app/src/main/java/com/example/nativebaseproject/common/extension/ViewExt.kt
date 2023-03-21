package com.example.nativebaseproject.common.extension

import android.os.SystemClock
import android.view.View

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