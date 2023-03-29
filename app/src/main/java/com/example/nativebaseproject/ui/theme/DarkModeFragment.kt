package com.example.nativebaseproject.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.common.extension.isOnDarkMode
import com.example.nativebaseproject.common.extension.setSingleClickListener
import com.example.nativebaseproject.data.local.AppDataStore
import com.example.nativebaseproject.databinding.FragmentDarkmodeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ThangNNT on 29/03/2023.
 */
@AndroidEntryPoint
class DarkModeFragment: BaseFragment<FragmentDarkmodeBinding>(FragmentDarkmodeBinding::inflate) {
    override fun setup() {
        appViewModel.setToolbarTitle(getString(R.string.dark_mode))
        when(AppDataStore.darkModeSetting){
            DarkModeSetting.TurnOff -> {
                binding.radioDarkModeOff.isChecked = true
            }
            DarkModeSetting.TurnOn -> {
                binding.radioDarkModeOn.isChecked = true
            }
            DarkModeSetting.SystemSetting -> {
                binding.radioUseSystemSetting.isChecked = true
            }
        }
        binding.radioDarkModeOn.setSingleClickListener{
            AppDataStore.darkModeSetting = DarkModeSetting.TurnOn
            requireActivity().apply {
                if (!isOnDarkMode(resources.configuration)){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
        binding.radioDarkModeOff.setSingleClickListener {
            AppDataStore.darkModeSetting = DarkModeSetting.TurnOff
            requireActivity().apply {
                if (isOnDarkMode(resources.configuration)){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        binding.radioUseSystemSetting.setSingleClickListener {
            AppDataStore.darkModeSetting = DarkModeSetting.SystemSetting
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }
    }
}