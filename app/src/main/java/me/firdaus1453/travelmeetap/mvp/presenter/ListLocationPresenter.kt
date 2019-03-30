package me.firdaus1453.travelmeetap.mvp.presenter

import android.os.Handler
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.firdaus1453.travelmeetap.Model.PlaceModel
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.mvp.contract.ListLocationContract
import me.firdaus1453.travelmeetap.network.ApiInterface
import me.firdaus1453.travelmeetap.utils.ApiUtils
import me.firdaus1453.travelmeetap.utils.GlobalVars
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by firdaus1453 on 3/30/2019.
 */
class ListLocationPresenter : ListLocationContract.Presenter {
    private var view: ListLocationContract.View? = null;
    private var viewLocation: View? = null
    private var mHandler: Handler
    private lateinit var mRunnable: Runnable

    constructor(view: ListLocationContract.View, view2: View) {
        this.view = view
        this.viewLocation = view2
        mHandler = Handler()
    }

    override fun loadData(search: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        var getDataPlaces = retrofit.create(ApiInterface::class.java)
        getDataPlaces.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    setData(it.data, search)
                },
                {
                    view!!.showError(
                        it.message,
                        viewLocation!!
                    )
                }
            )
    }

    override fun setData(places: List<PlaceModel>, search: String) {
        // show layout sesuai dengan nilai tempat 8310cdcb
        var data: List<PlaceModel> = places

        if (search != "") {
            data = places.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
        }

        if (data.size > 0) {
            view!!.setAdapter(
                data,
                viewLocation!!.findViewById(R.id.rvItemPlace)
            )
        } else {
            view!!.showError(
                viewLocation!!.resources.getString(R.string.tidak_ada_data),
                viewLocation!!
            )
        }
    }

    override fun onProgress(input: String) {
        // hide all screen
        view!!.setviewprogress(
            viewLocation!!
        )

        // post delay
        mRunnable = Runnable {
            loadData(input)
            view!!.hideprogressbar(viewLocation!!)
        }

        mHandler.postDelayed(
            mRunnable,
            (2000).toLong()
        )
    }
}