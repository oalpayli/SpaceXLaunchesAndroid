package com.ceng.ozi.spacexlaunchesandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ceng.ozi.spacexlaunchesandroid.R
import com.ceng.ozi.spacexlaunchesandroid.app.SpaceXLaunchesApplication
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.MainView {


    /**
     *  Views
     * */


    /**
     *  Variables
     * */
    @Inject
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SpaceXLaunchesApplication).component.inject(this)
        setContentView(R.layout.activity_main)
        presenter.injectView(this, this)
    }

    override fun onStart() {
        super.onStart()

        presenter.getLaunchesFromNetwork()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


    // region OVERRIDES

    override fun initUi() {

    }

    override fun showLaunches(launches: MutableList<LaunchDbModel>) {

    }

    override fun loading() {

    }

    override fun loading(message: String?) {

    }

    override fun dismissLoading() {

    }

    override fun showErrorMessage() {

    }

    // endregion


}
