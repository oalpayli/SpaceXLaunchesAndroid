package com.ceng.ozi.spacexlaunchesandroid.app.api

import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import io.reactivex.Observable
import retrofit2.http.GET


interface ServiceAPI {

    @GET("v3/launches")
    fun getLaunches(): Observable<List<LaunchDbModel>>

    @GET("v3/launches/latest")
    fun getLatestLaunch(): Observable<List<LaunchDbModel>>

}