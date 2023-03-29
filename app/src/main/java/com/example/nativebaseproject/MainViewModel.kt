package com.example.nativebaseproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nativebaseproject.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThangNNT on 21/03/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {
    private val _toolbarTitle: MutableLiveData<String> = MutableLiveData()
    val toolbarTitle: LiveData<String> = _toolbarTitle

    var shouldHideSplash = false
    init {
        viewModelScope.launch {
            delay(200)
            shouldHideSplash = true
        }
    }

    fun setToolbarTitle(title: String){
        _toolbarTitle.value = title
    }
}