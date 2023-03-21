package com.example.nativebaseproject.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.viewmodel.BaseViewModel

abstract class BaseMVVMActivity<VB: ViewBinding>(bindingFactory: (LayoutInflater) -> VB): BaseActivity<VB>(bindingFactory) {
    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    abstract fun setupObserver()
}