package com.example.nativebaseproject.common.binding_adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter




/**
 * Created by ThangNNT on 24/03/2023.
 */
@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, url: String) {
    imageView.setImageURI(Uri.parse(url))
}