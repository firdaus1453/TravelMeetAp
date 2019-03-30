package me.firdaus1453.travelmeetap.mvp.contract

import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

interface DetailContract {
    interface View {
        fun detailPlace(
            name:TextView,
            price:TextView,
            tlpDetail:TextView,
            district:TextView,
            address:TextView,
            desc:TextView,
            spOperational:Spinner,
            img:ImageView
        )
    }

    interface Presenter {
        fun setDetail()
    }
}