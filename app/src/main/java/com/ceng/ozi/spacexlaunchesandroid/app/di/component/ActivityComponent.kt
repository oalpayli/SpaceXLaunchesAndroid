package com.ceng.ozi.spacexlaunchesandroid.app.di.component

import com.ceng.ozi.spacexlaunchesandroid.app.di.PerActivity
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(modules = [ActivityModule::class])
interface ActivityComponent