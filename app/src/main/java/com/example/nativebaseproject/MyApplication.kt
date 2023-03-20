package com.example.nativebaseproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {


    companion object {
        @Volatile
        private var instance: MyApplication? = null

        @JvmStatic
        fun getInstance(): MyApplication = instance ?: synchronized(this) {
            instance ?: MyApplication().also {
                instance = it
            }
        }
    }
}