package com.example.nativebaseproject.base.extension

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
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

fun Context.shareSimpleText(text: String, subject: String = ""){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
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