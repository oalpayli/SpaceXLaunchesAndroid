package com.ceng.ozi.spacexlaunchesandroid.app.db.launch

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ceng.ozi.spacexlaunchesandroid.app.api.model.LaunchFailureDetailsModel
import com.ceng.ozi.spacexlaunchesandroid.app.api.model.LaunchSiteModel
import com.ceng.ozi.spacexlaunchesandroid.app.api.model.LinksModel
import com.ceng.ozi.spacexlaunchesandroid.app.api.model.ReuseModel
import com.ceng.ozi.spacexlaunchesandroid.app.db.converter.DynamicTypeConverter
import com.google.gson.annotations.SerializedName

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

@Entity(tableName = "Launch")
data class LaunchDbModel (

    @SerializedName("flight_number")
    @PrimaryKey(autoGenerate = false)
    val flightNumber: Long?,

    @SerializedName("mission_name")
    val missionName: String?,

    @SerializedName("upcoming")
    val upcoming: Boolean?,

    @SerializedName("launch_year")
    val launchYear: String?,

    @SerializedName("launch_date_unix")
    val launchDateUnix: Long?,

    @SerializedName("launch_date_utc")
    val launchDateUtc: String?,

    @SerializedName("launch_date_local")
    val launchDateLocal: String?,

    @SerializedName("is_tentative")
    val isTentative: Boolean?,

    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String?,

    @SerializedName("tbd")
    val tbd: Boolean?,

    @TypeConverters(DynamicTypeConverter::class)
    val ships: List<String>?,

    @Embedded
    val reuse: ReuseModel?,

    @SerializedName("launch_site")
    @Embedded
    val launchSite: LaunchSiteModel?,

    @SerializedName("launch_success")
    val launchSuccess: Boolean?,

    @SerializedName("launch_failure_details")
    @Embedded
    val launchFailureDetails: LaunchFailureDetailsModel?,

    @SerializedName("links")
    @Embedded
    val links: LinksModel?,

    @SerializedName("details")
    val details: String?,

    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String?,

    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Long?

)