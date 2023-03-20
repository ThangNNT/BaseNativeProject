package com.example.nativebaseproject.base.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat


fun Context.copyText(text: String){
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("copyText", text)
    clipboard?.setPrimaryClip(clip)
}