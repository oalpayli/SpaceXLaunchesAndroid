package com.ceng.ozi.spacexlaunchesandroid.app.di.module

import android.content.Context
import androidx.room.Room
import com.ceng.ozi.spacexlaunchesandroid.app.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    open fun providesContext(): Context = context

    @Provides
    @Singleton
    open fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "space-x-launches")
                    .allowMainThreadQueries()
                    .build()


    @Provides
    @Singleton
    open fun providesLaunchDao(database: AppDatabase) = database.launchDao()

}