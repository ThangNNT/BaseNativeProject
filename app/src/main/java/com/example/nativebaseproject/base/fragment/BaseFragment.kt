package com.example.nativebaseproject.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.nativebaseproject.base.activity.BaseActivity

abstract class BaseFragment<VB: ViewBinding>(private val bindingFactory: (LayoutInflater) -> VB): Fragment() {
    protected open val shouldHideKeyboardWhenTouchOutside = true
    private var mBinding: VB? = null

    val binding: VB
      get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = bindingFactory.invoke(inflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBaseActivity()?.apply {
            this.shouldHideKeyboardWhenTouchOutside = this@BaseFragment.shouldHideKeyboardWhenTouchOutside
        }
        setup()
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

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}