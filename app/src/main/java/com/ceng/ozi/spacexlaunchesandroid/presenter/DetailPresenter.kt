package com.ceng.ozi.spacexlaunchesandroid.presenter

import android.content.Context
import com.ceng.ozi.spacexlaunchesandroid.app.api.ServiceAPI
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDao
import com.ceng.ozi.spacexlaunchesandroid.ext.RxThread
import javax.inject.Inject

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

open class DetailPresenter @Inject constructor(private val rxThread: RxThread,
                                               private val launchDao: LaunchDao,
                                               private val api: ServiceAPI): BasePresenter(){

    private lateinit var view: DetailView

    interface DetailView: BaseView{
        fun initUi()
        fun showDetail(launch: LaunchDbModel)
    }

    fun injectView(view: DetailView, context : Context){
        this.view = view
        mContext = context
    }

    // region BUSINESS



    // endregion


    // region Api Call



    // endregion


    // region DB

    fun getLaunchFromDb(flightNumber: Long){
        view.loading()

        subscription.add(launchDao.findLaunch(flightNumber)
            .compose(rxThread.applySyncSingle())
            .subscribe({
                view.dismissLoading()

                view.showDetail(it)

            }, {
                view.dismissLoading()

                view.showErrorMessage(it.message)

            }))
    }

    // endregion

}