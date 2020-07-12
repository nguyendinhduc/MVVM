package com.vmodev.mvvm.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmodev.mvvm.ui.base.BaseViewUI
import com.vmodev.mvvm.ui.base.activity.BaseActivity

interface ViewFragment : BaseViewUI{
    fun onCreateViewControl(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
    fun onViewCreatedControl(view: View, savedInstanceState: Bundle?)
    fun onDestroyViewControl()
    fun reload(bundle: Bundle)
    fun getBaseActivity():BaseActivity
}