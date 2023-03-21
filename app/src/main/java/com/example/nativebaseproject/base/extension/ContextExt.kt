package com.example.nativebaseproject.base.extension

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.nativebaseproject.R


fun Context.copyText(text: String){
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("copyText", text)
    clipboard?.setPrimaryClip(clip)
}

fun Context.createProgressDialog(): AlertDialog {
    val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null)
    val mBuilder = AlertDialog.Builder(this)
        .setView(mDialogView)
    val progressDialog = mBuilder.show()
    progressDialog.setCancelable(false)
    progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    return progressDialog
}