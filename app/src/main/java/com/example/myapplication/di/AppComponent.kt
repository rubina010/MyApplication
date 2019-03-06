package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.MyApplication
import com.example.myapplication.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityBindingModule::class),
    (FragmentBindingModule::class), (ApplicationBindingModule::class), (ApiModule::class),
    (RxJavaModule::class), (AppModule::class)])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(App: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
