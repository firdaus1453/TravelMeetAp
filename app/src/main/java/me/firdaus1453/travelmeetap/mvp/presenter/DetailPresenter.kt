package me.firdaus1453.travelmeetap.mvp.presenter

import android.app.Activity
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.mvp.contract.DetailContract

/**
 * Created by firdaus1453 on 3/30/2019.
 */
class DetailPresenter : DetailContract.Presenter {
    private var view:DetailContract.View? = null
    private  var detailActivity: Activity? = null

    constructor(view:DetailContract.View, activity: Activity){
        this.view = view
        this.detailActivity = activity
    }

    override fun setDetail() {
        view!!.detailPlace(
            detailActivity!!.findViewById(R.id.tvDetailNamePlace),
            detailActivity!!.findViewById(R.id.tvPriceDetailPlace),
            detailActivity!!.findViewById(R.id.tvTlpDetailPlace),
            detailActivity!!.findViewById(R.id.tvDistrictDetailPlace),
            detailActivity!!.findViewById(R.id.tvAddressDetailPlace),
            detailActivity!!.findViewById(R.id.tvDescDetailPlace),
            detailActivity!!.findViewById(R.id.spOperationalTime),
            detailActivity!!.findViewById(R.id.imgDetailPlace)
        )
    }
}