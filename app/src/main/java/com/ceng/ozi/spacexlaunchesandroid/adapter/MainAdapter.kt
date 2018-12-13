package com.ceng.ozi.spacexlaunchesandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ceng.ozi.spacexlaunchesandroid.R
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.ext.Utility
import kotlinx.android.synthetic.main.recyclerview_launch_row_item.view.*

/**
 * Created by oguzhanalpayli on 12,December,2018
 */

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var launches = mutableListOf<LaunchDbModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_launch_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = launches[position]

        holder.tvMissionName.text = model.missionName!!
        holder.tvLaunchDate.text = Utility.currentUnixToStringDate(model.launchDateUnix!!)

        if (model.launchSuccess){
            holder.ivSuccessOrFailed.setImageResource(R.drawable.ic_success)
        } else {
            holder.ivSuccessOrFailed.setImageResource(R.drawable.ic_fail)
        }


        Glide.with(context)
            .asBitmap()
            .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher))
            .load(model.links?.missionPatch)
            .into(holder.ivMissionPatch)

    }


    // region BUSINESS

    fun addItems(items: MutableList<LaunchDbModel>){
        launches.clear()
        launches = items

        notifyDataSetChanged()
    }

    fun getItem(pos: Int): LaunchDbModel {
        return launches[pos]
    }

    // endregion


    class MainViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

        var ivMissionPatch: AppCompatImageView = itemView!!.ivMissionPatch_RecyclerViewLaunchesRowItem

        var ivSuccessOrFailed: AppCompatImageView = itemView!!.ivSuccessOrFailed_RecyclerViewLaunchesRowItem

        var tvMissionName: TextView = itemView!!.tvMissionName_RecyclerViewLaunchesRowItem

        var tvLaunchDate: TextView = itemView!!.tvLaunchDate_RecyclerViewLaunchesRowItem

    }

}