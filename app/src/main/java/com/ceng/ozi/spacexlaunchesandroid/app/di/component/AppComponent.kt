package com.ceng.ozi.spacexlaunchesandroid.app.di.component

import com.ceng.ozi.spacexlaunchesandroid.activities.DetailActivity
import com.ceng.ozi.spacexlaunchesandroid.activities.MainActivity
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ApplicationModule::class, AndroidModule::class, RxThreadModule::class, DatabaseModule::class])
interface AppComponent{

    fun inject(activityMain: MainActivity)

    fun inject(activityDetail: DetailActivity)

}