package com.example.nativebaseproject.base.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.R
import com.example.nativebaseproject.base.activity.BaseActivity

/**
 * Created by ThangNNT on 21/03/2023.
 */
abstract class BaseDialog<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB) : DialogFragment() {
    /**
     * Width: MATCH_PARENT and margin horizontal = 16dp.
     * Height: WRAP_CONTENT.
     */
    protected open val useDefaultWidthHeight = true

    private var mBinding: VB? = null
    val binding: VB
        get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = bindingFactory.invoke(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun onStart() {
        super.onStart()
        if (useDefaultWidthHeight){
            setMatchParentAndWrapContent()
        }
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    /**
     * set dialog MATCH_PARENT width and WRAP_CONTENT height.
     * Call this function at onStart
     */
    protected fun setMatchParentAndWrapContent(@DimenRes marginHorizontal: Int = com.intuit.sdp.R.dimen._16sdp){
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val marginInPixel = resources.getDimensionPixelSize(marginHorizontal)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, marginInPixel)
        dialog?.window?.setBackgroundDrawable(inset)
    }

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