package com.ceng.ozi.spacexlaunchesandroid.app.db.launch

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import io.reactivex.Single

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

@Dao
interface LaunchDao {


    // region FILTER

    @Query("SELECT * FROM Launch ORDER BY launchYear ASC")
    fun filterByAscYear() : Single<MutableList<LaunchDbModel>>

    @Query("SELECT * FROM Launch ORDER BY launchYear DESC")
    fun filterByDescYear() : Single<MutableList<LaunchDbModel>>

    @Query("SELECT * FROM Launch ORDER BY flightNumber ASC")
    fun filterByAscOrder() : Single<MutableList<LaunchDbModel>>

    @Query("SELECT * FROM Launch ORDER BY flightNumber DESC")
    fun filterByDescOrder() : Single<MutableList<LaunchDbModel>>

    // endregion


    @Query("SELECT * FROM Launch")
    fun findAllLaunch() : Single<MutableList<LaunchDbModel>>

    @Query("SELECT * FROM Launch WHERE flightNumber = :flightNumber")
    fun findLaunch(flightNumber: Long) : Single<LaunchDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunch(launchDbModel: LaunchDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launchDbModelList: List<LaunchDbModel>)

    @Query("DELETE FROM Launch WHERE flightNumber = :flightNumber")
    fun deleteLaunchByFlightNumber(flightNumber: Long)

    @Delete
    fun deleteLaunch(launchDbModel: LaunchDbModel)

}