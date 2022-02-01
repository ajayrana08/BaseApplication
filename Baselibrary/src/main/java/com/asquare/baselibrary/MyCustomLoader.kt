package com.asquare.baselibrary

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MyCustomLoader(private val mContext: Context?) {

    private var mDialog: Dialog? = null

    fun showSnackBar(view: View?, contentMsg: String) {
        if (null != mContext && null != view) {
            Snackbar.make(view, contentMsg, BaseTransientBottomBar.LENGTH_LONG)
                .setAction(
                    mContext.getString(R.string.action_okay)
                ) { }
                .setActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                .show()
        }
    }

    fun showToast(contentMsg: String) {
        if (null != mContext) {
            Toast.makeText(mContext, contentMsg, Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressDialog() {
        mDialog = Dialog(mContext!!)
        mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Inflate custom view
        val view = (mContext.getSystemService(
            Context
                .LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater)
            .inflate(R.layout.dialog_progress_loader, null)

        // Play GIF
        /* view.sdvLoader.controller = Fresco.newDraweeControllerBuilder()
                 .setUri(Uri.Builder()
                         .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                         .path(R.drawable.progress_loader.toString())
                         .build())
                 .setAutoPlayAnimations(true)
                 .build()*/

        mDialog!!.setContentView(view)
        mDialog!!.setCancelable(false)
        mDialog!!.show()
    }

    fun dismissProgressDialog() {
        mDialog?.dismiss()
    }
}