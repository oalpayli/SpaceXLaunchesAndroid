package com.ceng.ozi.spacexlaunchesandroid.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

object Utility{

    fun currentUnixToStringDate(unixTime : Long) : String {

        val date = java.util.Date(unixTime * 1000L)
        // the format of your date
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.timeZone = java.util.TimeZone.getTimeZone("GMT+2")
        return sdf.format(date)
    }

    // region NETWORK

    fun checkInternetConnection(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= 21) {
            val info = connectivity.allNetworks
            if (info != null) {
                for (i in info.indices) {
                    if (info[i] != null && connectivity.getNetworkInfo(info[i]).isConnected) {
                        return true
                    }
                }
            }
        } else {
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
            val activeNetwork = connectivity.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) {
                return true
            }
        }

        return false
    }

}