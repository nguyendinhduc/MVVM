package com.vmodev.mvvm.ui.utils

import androidx.fragment.app.FragmentManager
import com.vmodev.mvvm.R
import com.vmodev.mvvm.ui.base.AnimationScreen
import com.vmodev.mvvm.ui.base.fragment.BaseFragment
import com.vmodev.mvvm.ui.main.song.SongFragment

object OpenFragmentUtils {
    @JvmStatic
    fun getAnimationScreenFullOpen(): AnimationScreen {
        return AnimationScreen(R.anim.enter_to_left, R.anim.exit_to_left, R.anim.enter_to_right, R.anim.exit_to_right)
    }
    @JvmStatic
    fun openFirstLoginFragment(manager: FragmentManager) {
        val transaction = manager.beginTransaction()
        BaseFragment.openFragment(manager, transaction, SongFragment::class.java, null, false, true, null, R.id.content)
    }

}