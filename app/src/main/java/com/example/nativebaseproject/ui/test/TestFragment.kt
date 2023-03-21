package com.example.nativebaseproject.ui.test

import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.databinding.FragmentTestBinding

/**
 * Created by ThangNNT on 21/03/2023.
 */
class TestFragment: BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    override fun setup() {
        binding.text = "hello"
    }


}