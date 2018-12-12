package com.ceng.ozi.spacexlaunchesandroid.app.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
open class RxThreadModule{

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }

    @Singleton
    @Provides
    @Named(mainThread)
    open fun provideAndroidSchedulers(): Scheduler = AndroidSchedulers.mainThread()


    @Provides
    @Singleton
    @Named(ioThread)
    open fun provideSchedulersIO(): Scheduler = Schedulers.io()

}