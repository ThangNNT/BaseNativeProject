package com.example.nativebaseproject.ui.home

import androidx.fragment.app.viewModels
import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    override val inflateLayout: Int
        get() = R.layout.fragment_home

    override fun setup() {
        viewModel.text.observe(viewLifecycleOwner){

        }
        binding.textHome.setOnClickListener {
        }
    }
}