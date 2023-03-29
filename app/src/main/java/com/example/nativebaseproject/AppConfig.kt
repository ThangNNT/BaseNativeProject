package com.example.nativebaseproject

import com.example.nativebaseproject.common.util.getString
import com.example.nativebaseproject.ui.language.LanguageModel

/**
 * Created by ThangNNT on 23/03/2023.
 */
object AppConfig {
    /**
     * Change this by your app id.
     * It uses for share application function.
     */
    private const val YOUR_APP_ID = "com.example.myapp"
    const val APP_STORE_LINK = "https://play.google.com/store/apps/details?id=$YOUR_APP_ID"

    val appLanguages = listOf(
        LanguageModel(
            flag = R.drawable.ic_england_circle_48,
            country = getString(R.string.english),
            languageCode = "en"
        ),
        LanguageModel(
            flag = R.drawable.ic_vietnam_circle_flag_48,
            country = getString(R.string.vietnam),
            languageCode = "vi"
        ),
        LanguageModel(
            flag = R.drawable.ic_france_circle_48,
            country = getString(R.string.france),
            languageCode = "fr"
        )
    )
}