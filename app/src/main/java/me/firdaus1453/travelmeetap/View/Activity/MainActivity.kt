package me.firdaus1453.travelmeetap.View.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_location.*
import me.firdaus1453.travelmeetap.Model.PlaceModel
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.mvp.contract.MainContract
import me.firdaus1453.travelmeetap.mvp.presenter.MainPresenter
import me.firdaus1453.travelmeetap.utils.GlobalVars
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentManager = supportFragmentManager
        when (item.itemId) {
            R.id.navigation_list -> {
                presenter.changeFragment("listLocation",fragmentManager)
            }
            R.id.navigation_map -> {

            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MainPresenter(this,this)

        val fragmentManager = supportFragmentManager
        if(!GlobalVars.isHaveFragment){
            presenter.addFragment(fragmentManager)
        }else{
            presenter.changeFragment("listLocation",fragmentManager)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.changeLanguage -> {
                if (GlobalVars.nameLanguage.equals("default")) {
                    GlobalVars.nameLanguage = "in"
                    presenter.setLocale("in")
                } else {
                    GlobalVars.nameLanguage = "default"
                    presenter.setLocale("default")
                }
                this.recreate()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun Locale(locale: Locale) {
        val res = resources
        val conf = res.configuration
        conf.setLocale(locale)
        println("MainActivity: " + locale.toString())
        res.updateConfiguration(conf, res.displayMetrics)
    }

    override fun showData(listWisata: List<PlaceModel>) {
        rvItemPlace.hasFixedSize()
        rvItemPlace.layoutManager = LinearLayoutManager(this)
//        rvItemPlace.adapter = PlaceAdapter(this,listWisata){
//
//        }
    }
}
