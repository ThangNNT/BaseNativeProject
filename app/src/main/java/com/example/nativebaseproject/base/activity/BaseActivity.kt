package com.example.nativebaseproject.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB): AppCompatActivity() {
    private lateinit var mBinding: VB
    val binding: VB
        get() = mBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    abstract fun setup()
}