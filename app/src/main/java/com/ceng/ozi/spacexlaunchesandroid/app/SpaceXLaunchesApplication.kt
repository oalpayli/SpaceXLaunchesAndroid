package com.ceng.ozi.spacexlaunchesandroid.app

import android.app.Application
import android.content.Context
import com.ceng.ozi.spacexlaunchesandroid.app.di.component.AppComponent
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.AndroidModule
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.ApplicationModule
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.DatabaseModule
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.HttpModule
import com.ceng.ozi.spacexlaunchesandroid.app.di.module.RxThreadModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

open class SpaceXLaunchesApplication: Application() {

    init {
        instance = this
    }

    @Singleton
    @Component(modules = [HttpModule::class, ApplicationModule::class, AndroidModule::class, RxThreadModule::class, DatabaseModule::class])
    interface ApplicationComponent : AppComponent

    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

        component = createComponent()

    }

    protected open fun createComponent() : AppComponent {

        return DaggerSpaceXLaunchesApplication_ApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .build()

    }

    companion object {
        private var instance: SpaceXLaunchesApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}