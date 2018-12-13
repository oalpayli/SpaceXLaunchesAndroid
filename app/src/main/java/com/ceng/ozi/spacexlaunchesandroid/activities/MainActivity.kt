package com.ceng.ozi.spacexlaunchesandroid.activities

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.ceng.ozi.spacexlaunchesandroid.R
import com.ceng.ozi.spacexlaunchesandroid.adapter.MainAdapter
import com.ceng.ozi.spacexlaunchesandroid.app.SpaceXLaunchesApplication
import com.ceng.ozi.spacexlaunchesandroid.app.db.launch.LaunchDbModel
import com.ceng.ozi.spacexlaunchesandroid.ext.Constants
import com.ceng.ozi.spacexlaunchesandroid.ext.ItemClickSupport
import com.ceng.ozi.spacexlaunchesandroid.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Locale.filter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.MainView, ItemClickSupport.OnItemClickListener,
    View.OnClickListener {

    /**
     *  Views
     * */


    /**
     *  Variables
     * */
    @Inject
    lateinit var presenter: MainPresenter
    private var adapter: MainAdapter? = null
    private var selectedFilterIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SpaceXLaunchesApplication).component.inject(this)
        setContentView(R.layout.activity_main)
        presenter.injectView(this, this)

        initUi()

        presenter.getLaunches()
    }


    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // region BUSINESS

    private fun showFilterDialog() {

        val dialog = MaterialDialog.Builder(this)
            .title(R.string.filter_list_dialog_title)
            .negativeText(R.string.dialog_cancel_button_text)
            .onNegative { dialog, which ->

            }
            .itemsCallbackSingleChoice(selectedFilterIndex
            ) { dialog, itemView, which, text ->

                selectedFilterIndex = dialog.selectedIndex

                presenter.filter(selectedFilterIndex)

                return@itemsCallbackSingleChoice true
            }
            .items(R.array.filter_list)
            .build()

        dialog.show()
    }

    // endregion

    // region ACTIONS

    override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
        val bundle = Bundle()
        bundle.putLong(Constants.BUNDLE_EXTRA_FLIGHT_NUMBER, adapter!!.getItem(position).flightNumber!!)
        startDetailActivity(bundle)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabfilter_MainActivity -> {
                showFilterDialog()
            }
        }
    }

    // endregion


    // region OVERRIDES

    override fun initUi() {
        ImageViewCompat.setImageTintList(
            fabfilter_MainActivity,
            ColorStateList.valueOf(Color.WHITE)
        )

        recyclerView_MainActivity.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this)
        recyclerView_MainActivity.adapter = adapter

        ItemClickSupport.addTo(recyclerView_MainActivity).setOnItemClickListener(this)

        fabfilter_MainActivity.setOnClickListener(this)
    }

    override fun showLaunches(launches: MutableList<LaunchDbModel>) {
        recyclerView_MainActivity.visibility = View.VISIBLE
        adapter!!.addItems(launches)
    }

    override fun loading() {
        progressBar_MainActivity.visibility = View.VISIBLE
        recyclerView_MainActivity.visibility = View.GONE
        textView_MainActivity.visibility = View.GONE
    }

    override fun loading(message: String?) {

    }

    override fun dismissLoading() {
        progressBar_MainActivity.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        textView_MainActivity.visibility = View.VISIBLE
        recyclerView_MainActivity.visibility = View.GONE

        textView_MainActivity.text = message!!
    }

    // endregion


}
