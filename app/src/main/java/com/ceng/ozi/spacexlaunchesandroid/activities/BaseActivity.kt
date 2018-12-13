package com.ceng.ozi.spacexlaunchesandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by oguzhanalpayli on 13,December,2018
 */

open class BaseActivity: AppCompatActivity(){

    // region Nav

    fun startDetailActivity(bundle: Bundle){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    // endregion

}