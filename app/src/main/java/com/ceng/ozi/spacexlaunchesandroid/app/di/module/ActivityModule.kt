package com.ceng.ozi.spacexlaunchesandroid.app.di.module

import android.app.Activity
import android.content.Context
import com.ceng.ozi.spacexlaunchesandroid.app.di.ActivityContext
import com.ceng.ozi.spacexlaunchesandroid.app.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity){

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

}