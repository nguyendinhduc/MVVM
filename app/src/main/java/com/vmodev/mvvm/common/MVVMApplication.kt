package com.vmodev.mvvm.common

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vmodev.mvvm.di.compoment.AppComponent
import com.vmodev.mvvm.di.compoment.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MVVMApplication : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {
    lateinit var appComponent: AppComponent
        get

    @Inject
    internal lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>
    @Inject
    internal lateinit var fragmentyDispatchingInjector: DispatchingAndroidInjector<Fragment>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }

    fun appDatabase() = appComponent.appDatabase()
    fun interactCommon() = appComponent.interactCommon()
    fun schedule() = appComponent.schedule()

    override fun activityInjector(): AndroidInjector<Activity?>? {
        return activityDispatchingInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentyDispatchingInjector
    }
}