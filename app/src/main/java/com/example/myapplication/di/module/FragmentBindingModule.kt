package com.example.myapplication.di.module

import com.example.myapplication.TvAppFragment
import com.example.myapplication.di.scope.FragmentScoped
import com.example.myapplication.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindTvAppFragment(): TvAppFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}
