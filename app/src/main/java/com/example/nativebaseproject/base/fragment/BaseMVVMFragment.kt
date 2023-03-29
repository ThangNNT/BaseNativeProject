package com.example.nativebaseproject.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.viewmodel.BaseViewModel

abstract class BaseMVVMFragment<VB: ViewBinding>(bindingFactory: (LayoutInflater) -> VB): BaseFragment<VB>(bindingFactory) {
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    abstract fun setupObserver()
}