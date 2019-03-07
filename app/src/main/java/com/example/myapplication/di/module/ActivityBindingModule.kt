package com.example.myapplication.di.module

import com.example.myapplication.di.scope.ActivityScoped
import com.example.myapplication.ui.book.BookDetailsFragment
import com.example.myapplication.ui.dashboard.DashboardActivity
import com.example.myapplication.ui.home.DashboardMainActivity
import com.example.myapplication.ui.learning.LearningActivity
import com.example.myapplication.ui.login.LoginActivity
import com.example.myapplication.ui_tv.TvAppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindLearningActivity(): LearningActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindDashboardActivity(): DashboardActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindDashboardMainActivity(): DashboardMainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindTvAppActivity(): TvAppActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindBookDetailsActivity(): BookDetailsFragment
}
