package com.asquare.baselibrary

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding


abstract class BaseDialogFragment : DialogFragment() {
    private val mMyCustomLoader: MyCustomLoader by lazy { MyCustomLoader(context) }
    //protected val mUserPrefsManager: UserPrefsManager by lazy { UserPrefsManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return setViewBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseAppTheme)

        // Making fragment dialog transparent and of fullscreen width
        val dialog = dialog
        if (null != dialog) {
            if (isFullScreenDialog) {
                dialog.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            } else {
                dialog.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            //dialog.window!!.setWindowAnimations(R.style.DialogFragmentAnimations)
        }


        init()
       // observeBaseProperties()
    }

    private fun observeBaseProperties() {
        // Observe message
       /* viewModel?.getSuccessMessage()?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            showMessage(null, it)
        })

        // Observe any general exception
        viewModel?.getErrorHandler()?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (null != it) {
                showMessage(resId = it.getErrorResource(), isShowSnackbarMessage = false)
            }
        })

        // Observe visibility of loader
        viewModel?.isShowLoader()?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it!!) {
                showProgressLoader()
            } else {
                hideProgressLoader()
            }
        })*/

        //Observer
        //observeProperties()
    }

    fun showMessage(
        resId: Int? = null, message: String? = null,
        isShowSnackbarMessage: Boolean = false
    ) {
        if (isShowSnackbarMessage) {
            mMyCustomLoader.showSnackBar(view, message ?: getString(resId!!))
        } else {
            mMyCustomLoader.showToast(message ?: getString(resId!!))
        }
    }

    protected fun showProgressLoader() {
        mMyCustomLoader.showProgressDialog()
    }

    protected fun hideProgressLoader() {
        mMyCustomLoader.dismissProgressDialog()
    }


    abstract fun setViewBinding(): ViewBinding
    abstract fun init(savedInstanceState: Bundle?)
    abstract val isFullScreenDialog: Boolean
    abstract fun init()
    //abstract val viewModel: BaseViewModel?
    //abstract fun observeProperties()

}