package com.ceng.ozi.spacexlaunchesandroid.app.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oguzhanalpayli on 12,December,2018
 */
data class LinksModel(

    @SerializedName("mission_patch")
    val missionPatch: String = "",

    @SerializedName("mission_patch_small")
    val missionPatchSmall: String = "",

    @SerializedName("article_link")
    val articleLink: String = "",

    @SerializedName("wikipedia")
    val wikipedia: String = "",

    @SerializedName("video_link")
    val videoLink: String = ""

)