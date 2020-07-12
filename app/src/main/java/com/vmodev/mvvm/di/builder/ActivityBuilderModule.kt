package com.vmodev.mvvm.di.builder

import com.vmodev.mvvm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}