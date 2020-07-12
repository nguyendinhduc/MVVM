package com.vmodev.mvvm.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vmodev.mvvm.di.model.ViewModelFactory
import com.vmodev.mvvm.di.model.ViewModelKey
import com.vmodev.mvvm.ui.main.song.SongModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SongModel::class)
    abstract fun bindsSongViewModel(songViewModel: SongModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}