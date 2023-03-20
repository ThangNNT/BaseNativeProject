package com.example.nativebaseproject

import android.app.Application
import androidx.lifecycle.*
import com.example.nativebaseproject.common.timber.DebugTree
import com.example.nativebaseproject.common.timber.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application(), DefaultLifecycleObserver {

    override fun onCreate() {
        super<Application>.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        initTimber()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Timber.d("onStart: application go foreground!")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Timber.d("onStop: application go background!")
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }


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