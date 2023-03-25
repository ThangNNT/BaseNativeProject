package com.example.nativebaseproject.common.util

import android.content.Context
import androidx.annotation.StringRes
import com.example.nativebaseproject.MyApplication
/**
 * Created by ThangNNT on 23/03/2023.
 */

fun getApplicationContext(): Context {
    return MyApplication.getInstance().applicationContext
}

fun getString(@StringRes id: Int): String {
    return getApplicationContext().getString(id)
}