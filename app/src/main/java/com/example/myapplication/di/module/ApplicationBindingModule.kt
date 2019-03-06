package com.example.myapplication.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationBindingModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}
