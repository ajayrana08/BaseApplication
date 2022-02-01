package com.asquare.baselibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {

    abstract fun setViewBinding(): ViewBinding
    abstract fun init(savedInstanceState: Bundle?)

    private val mMyCustomLoader: MyCustomLoader by lazy { MyCustomLoader(context) }
}