package com.ceng.ozi.spacexlaunchesandroid.app.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oguzhanalpayli on 12,December,2018
 */
data class ReuseModel (

    val core: Boolean?,

    @SerializedName("side_core1")
    val sideCore1: Boolean?,

    @SerializedName("side_core2")
    val sideCore2: Boolean?,

    @SerializedName("fairings")
    val fairings: Boolean?,

    @SerializedName("capsule")
    val capsule: Boolean?

)