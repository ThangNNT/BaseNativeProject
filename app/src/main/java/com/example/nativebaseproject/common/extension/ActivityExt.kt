package com.example.nativebaseproject.common.extension

import android.content.res.Configuration


fun isOnDarkMode(configuration: Configuration): Boolean {
    return configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}



