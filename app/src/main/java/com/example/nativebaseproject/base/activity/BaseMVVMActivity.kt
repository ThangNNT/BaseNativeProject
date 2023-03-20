package com.example.nativebaseproject.base.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.nativebaseproject.base.viewmodel.BaseViewModel

abstract class BaseMVVMActivity<DB: ViewDataBinding>: BaseActivity<DB>() {
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    abstract fun setupObserver()
}