package com.example.nativebaseproject.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    private lateinit var mBinding: VB
    val binding: VB
        get() = mBinding
    abstract val inflateLayout: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, inflateLayout)
        setup()
    }

    abstract fun setup()
}