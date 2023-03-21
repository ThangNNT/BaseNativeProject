package com.example.nativebaseproject.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.common.extension.setSingleClickListener
import com.example.nativebaseproject.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun setup() {
        viewModel.text.observe(viewLifecycleOwner){

        }
        binding.textHome.setSingleClickListener {
            findNavController().navigate(HomeFragmentDirections.actionOpenTestDialog())
        }
    }
}