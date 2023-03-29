package com.example.nativebaseproject.ui.language

import androidx.lifecycle.viewModelScope
import com.example.nativebaseproject.AppConfig
import com.example.nativebaseproject.base.viewmodel.BaseViewModel
import com.example.nativebaseproject.common.util.getCurrentLanguageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThangNNT on 21/03/2023.
 */
@HiltViewModel
class LanguageViewModel @Inject constructor(): BaseViewModel() {
    var isFirstCreate = true
    var currentLanguageModel: LanguageModel = getCurrentLanguageModel()
    var shouldHideSplash = false
    init {
        viewModelScope.launch {
            delay(200)
            shouldHideSplash = true
        }
    }
}