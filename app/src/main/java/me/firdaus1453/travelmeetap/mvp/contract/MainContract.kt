package me.firdaus1453.travelmeetap.mvp.contract


import android.support.v4.app.FragmentManager
import me.firdaus1453.travelmeetap.Model.PlaceModel
import java.util.*

interface MainContract {
    interface View{
        fun Locale(locale:Locale)
        fun showData(listWisata: List<PlaceModel>)
    }

    interface Presenter{
        fun setLocale(lang:String?)
        fun addFragment(fragmentManager: FragmentManager)
        fun changeFragment(name:String,fragmentManager: FragmentManager)
        fun getData(wisata : String)
    }
}