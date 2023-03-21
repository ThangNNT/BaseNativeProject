package com.example.nativebaseproject.ui.test

import androidx.lifecycle.lifecycleScope
import com.example.nativebaseproject.base.dialog.BaseBottomSheet
import com.example.nativebaseproject.base.dialog.BaseDialog
import com.example.nativebaseproject.base.fragment.BaseFragment
import com.example.nativebaseproject.common.extension.setSingleClickListener
import com.example.nativebaseproject.databinding.DialogTestBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by ThangNNT on 21/03/2023.
 */
class TestDialog: BaseDialog<DialogTestBinding>(DialogTestBinding::inflate) {

    override fun setup() {
        binding.text = "hello"

        binding.tvTest.setSingleClickListener {
            showProgressDialog()
            lifecycleScope.launch(Dispatchers.Main){
                delay(2000)
                hideProgressDialog()
            }
        }
    }
}