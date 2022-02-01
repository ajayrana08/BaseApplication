package com.asquare.baselibrary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseAppCompactActivity : AppCompatActivity() {

    abstract val isMakeStatusBarTransparent: Boolean
    abstract fun setViewBinding(): ViewBinding
    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setViewBinding().root)
        if (GeneralFunctions.isAboveLollipopDevice) {
            val window = window
            if (isMakeStatusBarTransparent) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.colorTransparent)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.setDecorFitsSystemWindows(false)
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }

            }
        }
        init()

    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    fun AppCompatActivity.doFragmentTransaction(
        fragManager: FragmentManager = supportFragmentManager,
        @IdRes containerViewId: Int,
        fragment: Fragment,
        tag: String = "",
        @AnimatorRes enterAnimation: Int = 0,
        @AnimatorRes exitAnimation: Int = 0,
        @AnimatorRes popEnterAnimation: Int = 0,
        @AnimatorRes popExitAnimation: Int = 0,
        isAddFragment: Boolean = true,
        isAddToBackStack: Boolean = true,
        allowStateLoss: Boolean = false
    ) {

        val fragmentTransaction = fragManager.beginTransaction()
            .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)

        if (isAddFragment) {
            fragmentTransaction.add(containerViewId, fragment, tag)
        } else {
            fragmentTransaction.replace(containerViewId, fragment, tag)
        }

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }

        if (allowStateLoss) {
            fragmentTransaction.commitAllowingStateLoss()
        } else {
            fragmentTransaction.commit()
        }
    }


    fun AppCompatActivity.openShareDialog(
        shareHeading: String = getString(R.string.share_via),
        shareSubject: String = getString(R.string.app_name),
        messageToShare: String
    ) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
        share.putExtra(Intent.EXTRA_TEXT, messageToShare)
        startActivity(Intent.createChooser(share, shareHeading))
    }


}
