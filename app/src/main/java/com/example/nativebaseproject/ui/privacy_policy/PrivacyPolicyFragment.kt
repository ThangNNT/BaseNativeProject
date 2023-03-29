package com.example.nativebaseproject.ui.privacy_policy

import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : BaseFragment<FragmentPrivacyPolicyBinding>(FragmentPrivacyPolicyBinding::inflate) {
    override fun setup() {
        appViewModel.setToolbarTitle(getString(R.string.privacy_policies))
    }
}