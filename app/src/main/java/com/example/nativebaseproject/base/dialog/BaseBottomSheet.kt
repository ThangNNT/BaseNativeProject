package com.example.nativebaseproject.base.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.activity.BaseActivity
import com.example.nativebaseproject.common.extension.hideKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by ThangNNT on 21/03/2023.
 */
abstract class BaseBottomSheet<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB) : BottomSheetDialogFragment() {
    protected open val shouldHideKeyboardWhenTouchOutside = true

    /**
     * Normal bottom sheet only reach out 60% of the screen despite the content is not show full.
     * set [isWrapContentBottomSheet] = false if you want normal behavior or true to show wrap content bottom sheet
     */
    protected open val isWrapContentBottomSheet = true

    private var mBinding: VB? = null
    val binding: VB
        get() = mBinding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : BottomSheetDialog(requireContext(), theme){
            override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
                if (shouldHideKeyboardWhenTouchOutside){
                    val view: View? = currentFocus
                    val ret = super.dispatchTouchEvent(ev)
                    if (view is EditText) {
                        currentFocus?.let {
                            val w: View = it
                            val screenCoordinates = IntArray(2)
                            w.getLocationOnScreen(screenCoordinates)
                            val x: Float = ev.rawX + w.left - screenCoordinates[0]
                            val y: Float = ev.rawY + w.top - screenCoordinates[1]
                            if (ev.action == MotionEvent.ACTION_UP
                                && (x < w.left || x >= w.right || y < w.top || y > w.bottom)
                            ) {
                                view.hideKeyboard()
                            }
                        }
                    }
                    return ret
                }
                else {
                    return super.dispatchTouchEvent(ev)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = bindingFactory.invoke(layoutInflater)
        return binding.root
    }

    /**
     * override this method to set bottom sheet dialog height to wrap_content
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isWrapContentBottomSheet){
            dialog?.setOnShowListener {
                val dialog = it as BottomSheetDialog
                val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.let { sheet ->
                    dialog.behavior.peekHeight = sheet.height
                    sheet.parent.parent.requestLayout()
                }
            }
        }
        setup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    abstract fun setup()

    /**
     * @return parent [BaseActivity] or @null if parent activity is not [BaseActivity]
     */
    private fun getBaseActivity(): BaseActivity<*>?{
        val activity = requireActivity()
        return if (activity is BaseActivity<*>){
            activity
        } else null
    }

    protected fun showProgressDialog(){
        getBaseActivity()?.showProgressDialog()
    }

    protected fun hideProgressDialog(){
        getBaseActivity()?.hideProgressDialog()
    }
}