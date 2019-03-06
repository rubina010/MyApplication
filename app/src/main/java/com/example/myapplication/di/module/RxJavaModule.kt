package com.example.myapplication.di.module

import com.example.myapplication.utils.BaseSchedulerProvider
import com.example.myapplication.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxJavaModule {
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider() = SchedulerProvider()

    @Provides
    fun provideBaseSchedulerPerovider(): BaseSchedulerProvider = SchedulerProvider()
}