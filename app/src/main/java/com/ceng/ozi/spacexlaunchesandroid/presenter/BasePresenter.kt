package com.ceng.ozi.spacexlaunchesandroid.presenter

import android.content.Context
import com.ceng.ozi.spacexlaunchesandroid.ext.Utility
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

open class BasePresenter {

    interface BaseView {
        fun loading()
        fun loading(message: String?)
        fun dismissLoading()
        fun showErrorMessage(message: String?)
    }

    companion object {
        const val DELAY_TIME: Long = 2
    }

    var mContext: Context? = null
    val subscription = CompositeDisposable()

    fun onDestroy(){
        subscription.clear()
    }

    // region BUSINESS

    fun isNetworkAvailable(): Boolean{
        return Utility.checkInternetConnection(mContext!!)
    }

    // endregion

}