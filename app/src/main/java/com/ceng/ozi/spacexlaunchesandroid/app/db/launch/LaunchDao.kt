package com.ceng.ozi.spacexlaunchesandroid.app.db.launch

import androidx.room.*
import io.reactivex.Single

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

@Dao
interface LaunchDao {

    @Query("SELECT * FROM Launch")
    fun findAllLaunch() : Single<MutableList<LaunchDbModel>>

    @Query("SELECT * FROM Launch ORDER BY launchDateUtc ASC")
    fun findLaunchByDate() : Single<MutableList<LaunchDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunch(launchDbModel: LaunchDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launchDbModelList: List<LaunchDbModel>)

    @Query("DELETE FROM Launch WHERE flightNumber = :flightNumber")
    fun deleteLaunchByFlightNumber(flightNumber: Long)

    @Delete
    fun deleteLaunch(launchDbModel: LaunchDbModel)

}