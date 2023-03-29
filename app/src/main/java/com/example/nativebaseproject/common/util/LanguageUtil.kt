package com.example.nativebaseproject.common.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.nativebaseproject.AppConfig
import com.example.nativebaseproject.data.local.AppDataStore
import com.example.nativebaseproject.ui.language.LanguageModel
import java.util.Locale

/**
 * Created by ThangNNT on 24/03/2023.
 */
fun getDefaultLanguage(): LanguageModel {
    val deviceLang = Locale.getDefault().language
    val finding =  AppConfig.appLanguages.find { deviceLang == it.languageCode }
    return finding?: AppConfig.appLanguages.first()
}

fun getCurrentLanguageModel(): LanguageModel {
    val code = AppDataStore.currentLanguage
    return (AppConfig.appLanguages.find { it.languageCode == code }?: getDefaultLanguage())
}

fun changeLanguage(language: LanguageModel) {
    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language.languageCode)
    // Call this on the main thread as it may require Activity.restart()
    AppCompatDelegate.setApplicationLocales(appLocale)
}