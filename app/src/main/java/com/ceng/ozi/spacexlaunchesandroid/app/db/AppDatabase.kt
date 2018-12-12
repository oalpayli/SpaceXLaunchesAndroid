package com.ceng.ozi.spacexlaunchesandroid.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceng.ozi.spacexlaunchesandroid.app.db.converter.DynamicTypeConverter
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDao
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel


@Database(entities = [LaunchDbModel::class], version = 1, exportSchema = false)
@TypeConverters(DynamicTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun launchDao() : LaunchDao


}