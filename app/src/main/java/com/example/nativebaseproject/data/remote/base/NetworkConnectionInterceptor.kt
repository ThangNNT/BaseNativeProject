package com.example.nativebaseproject.data.remote.base

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException


class NetworkConnectionInterceptor(
    private val context: Context
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetConnected(context = context)) {
            Timber.d("Your connection is not available!")
            throw NoInternetException("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInternetConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    }
}

class NoInternetException(message: String) : IOException(message)