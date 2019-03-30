package me.firdaus1453.travelmeetap.mvp.presenter

import android.app.Activity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.mvp.contract.MainContract
import me.firdaus1453.travelmeetap.utils.GlobalVars
import me.firdaus1453.travelmeetap.View.Fragment.ListLocationFragment
import java.util.*

/**
 * Created by firdaus1453 on 3/30/2019.
 */
class MainPresenter : MainContract.Presenter {
    override fun getData(wisata: String) {

    }

    private var view: MainContract.View
    private var mainActivity: Activity

    constructor(view: MainContract.View, activity: Activity) {
        this.view = view
        this.mainActivity = activity
    }

    override fun setLocale(lang: String?) {
        var myLocale: Locale?
        if (lang != "in") {
            myLocale = Locale.getDefault()
        } else {
            myLocale = Locale(lang)
        }

        view.Locale(myLocale!!)
    }

    override fun addFragment(fragmentManager: FragmentManager) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val listLocationFragment = ListLocationFragment()
        fragmentTransaction.add(R.id.mainFrame, listLocationFragment, listLocationFragment.javaClass.simpleName)
        GlobalVars.isHaveFragment = true
        fragmentTransaction.commit()
    }

    override fun changeFragment(name: String,fragmentManager: FragmentManager) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if(name.equals("listLocation")){
            val listLocationFragment = ListLocationFragment()
            fragmentTransaction.replace(
                R.id.mainFrame, listLocationFragment,
                listLocationFragment.javaClass.simpleName
            )
        }else{
            // empty code
        }
        GlobalVars.isHaveFragment = true
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}