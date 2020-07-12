package com.vmodev.mvvm.di.builder

import com.vmodev.mvvm.ui.main.song.SongFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeSongFragment(): SongFragment
}