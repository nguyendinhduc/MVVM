package com.vmodev.mvvm.ui.main

import android.os.Bundle
import com.vmodev.mvvm.R
import com.vmodev.mvvm.ui.base.activity.BaseActivity
import com.vmodev.mvvm.ui.utils.OpenFragmentUtils

class MainActivity : BaseActivity() {

    override fun getLayoutMain() = R.layout.activity_main

    override fun setEvents() {
    }

    override fun onCreateControl(savedInstanceState: Bundle?) {
        super.onCreateControl(savedInstanceState)
    }
    override fun initComponents() {
        OpenFragmentUtils.openFirstSongFragment(supportFragmentManager)
    }
}
