package com.example.nativebaseproject.ui.home

import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val inflateLayout: Int
        get() = R.layout.fragment_home

    override fun setup() {
        binding.textHome.setOnClickListener {
        }
    }
}