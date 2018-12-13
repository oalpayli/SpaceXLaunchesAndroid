package com.ceng.ozi.spacexlaunchesandroid.activities

import android.os.Bundle
import android.view.View
import com.ceng.ozi.spacexlaunchesandroid.R
import com.ceng.ozi.spacexlaunchesandroid.app.SpaceXLaunchesApplication
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.ext.Constants
import com.ceng.ozi.spacexlaunchesandroid.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailPresenter.DetailView {

    /**
     *   Views
     * */


    /**
     *   Variables
     * */
    @Inject
    lateinit var presenter: DetailPresenter
    private var flightNumber: Long = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SpaceXLaunchesApplication).component.inject(this)
        setContentView(R.layout.activity_detail)
        presenter.injectView(this, this)

        getIntentExtra()

        initUi()

        presenter.getLaunchFromDb(flightNumber)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    // region BUSINESS

    private fun getIntentExtra() {
        flightNumber = intent.extras?.getLong(Constants.BUNDLE_EXTRA_FLIGHT_NUMBER)!!
    }

    // endregion


    // region OVERRIDES

    override fun initUi() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun showDetail(launch: LaunchDbModel) {
        scrollView_DetailActivity.visibility = View.VISIBLE

        title = "${launch.missionName}"

        tvDescription_DetailActivity.text = "${launch.details}"
    }

    override fun loading() {
        progressBar_DetailActivity.visibility = View.VISIBLE
        scrollView_DetailActivity.visibility = View.GONE
        tvErrorMessage_DetailActivity.visibility = View.GONE
    }

    override fun loading(message: String?) {

    }

    override fun dismissLoading() {
        progressBar_DetailActivity.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        tvErrorMessage_DetailActivity.visibility = View.VISIBLE
        scrollView_DetailActivity.visibility = View.GONE

        tvErrorMessage_DetailActivity.text = message!!
    }

    // endregion

}
