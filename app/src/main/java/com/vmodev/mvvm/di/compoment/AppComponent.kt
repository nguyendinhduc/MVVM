package com.vmodev.mvvm.di.compoment

import android.app.Application
import android.content.Context
import com.vmodev.mvvm.common.MVVMApplication
import com.vmodev.mvvm.data.local.AppDatabase
import com.vmodev.mvvm.data.remote.InteractCommon
import com.vmodev.mvvm.di.builder.ActivityBuilderModule
import com.vmodev.mvvm.di.builder.FragmentBuilderModule
import com.vmodev.mvvm.di.model.AppModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModel::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class, FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication>{
    fun inject(application: MVVMApplication)

    fun context(): Context
    fun appDatabase(): AppDatabase
    fun interactCommon(): InteractCommon
    fun schedule(): Executor

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}