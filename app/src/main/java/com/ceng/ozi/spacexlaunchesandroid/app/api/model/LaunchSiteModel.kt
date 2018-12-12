package com.ceng.ozi.spacexlaunchesandroid.app.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oguzhanalpayli on 12,December,2018
 */
data class LaunchSiteModel(

    @SerializedName("site_id")
    val siteId: String?,

    @SerializedName("site_name")
    val siteName: String?,

    @SerializedName("site_name_long")
    val siteNameLong: String?

)