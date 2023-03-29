package com.example.nativebaseproject.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.viewmodel.BaseViewModel

/**
 * Created by ThangNNT on 21/03/2023.
 */
abstract class BaseMVVMDialog<VB: ViewBinding>(bindingFactory: (LayoutInflater) -> VB): BaseDialog<VB>(bindingFactory) {
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    abstract fun setupObserver()
}