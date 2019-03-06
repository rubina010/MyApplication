package com.example.myapplication

import androidx.fragment.app.Fragment
import com.example.myapplication.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MyApplication : DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .application(this)
            .build()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }
}
