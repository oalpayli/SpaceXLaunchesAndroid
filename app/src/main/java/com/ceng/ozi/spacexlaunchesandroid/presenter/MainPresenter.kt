package com.ceng.ozi.spacexlaunchesandroid.presenter

import android.content.Context
import android.util.Log
import com.ceng.ozi.spacexlaunchesandroid.app.api.ServiceAPI
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDao
import com.ceng.ozi.spacexlaunchesandroid.ext.RxThread
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

open class MainPresenter @Inject constructor(private val rxThread: RxThread,
                                             private val launchDao: LaunchDao,
                                             private val api: ServiceAPI): BasePresenter(){

    private lateinit var view: MainView

    interface MainView: BaseView{
        fun initUi()
        fun showLaunches(launches: MutableList<LaunchDbModel>)
    }

    fun injectView(view: MainView, context : Context){
        this.view = view
        mContext = context
    }

    // region BUSINESS

    fun getLaunches(){

        if (isNetworkAvailable())
        {
            getLaunchesFromNetwork()
        }
        else
        {
            getLaunchesFromDb()
        }

    }

    // endregion


    // region Api Call

    private fun getLaunchesFromNetwork(){
        view.loading()

        subscription.add(api.getLaunches()
            .compose(rxThread.applySync())
            .delay(DELAY_TIME, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe({

                view.dismissLoading()

                insertAllLaunches(it)
                view.showLaunches(it.toMutableList())

            }, {

                view.dismissLoading()

                view.showErrorMessage(it.message)

            }))
    }

    // endregion


    // region DB

    private fun getLaunchesFromDb(){
        view.loading()

        subscription.add(launchDao.findAllLaunch()
            .compose(rxThread.applySyncSingle())
            .delay(DELAY_TIME, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissLoading()

                view.showLaunches(it.toMutableList())

            }, {
                view.dismissLoading()

                view.showErrorMessage(it.message)

            }))
    }

    private fun insertAllLaunches(launches: List<LaunchDbModel>){
        launchDao.insertLaunches(launches.toList())
    }

    // endregion

}