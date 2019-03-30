package me.firdaus1453.travelmeetap.View.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_list_location.*
import kotlinx.android.synthetic.main.fragment_list_location.view.*
import me.firdaus1453.travelmeetap.Model.PlaceModel
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.View.Activity.DetailActivity
import me.firdaus1453.travelmeetap.View.Activity.InsertWisataActivity
import me.firdaus1453.travelmeetap.adapter.PlaceAdapter
import me.firdaus1453.travelmeetap.mvp.contract.ListLocationContract
import me.firdaus1453.travelmeetap.mvp.presenter.ListLocationPresenter

class ListLocationFragment : Fragment(), ListLocationContract.View {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_location, container, false)
        mHandler = Handler()
        val presenter = ListLocationPresenter(this, view)
        presenter.onProgress("")

        // background thread
        view.swipeRefreshLayout.setOnRefreshListener {
            mRunnable = Runnable {
                view.swipeRefreshLayout.isRefreshing = false
                presenter.onProgress("")
            }

            mHandler.postDelayed(
                mRunnable,
                (2000).toLong()
            )
        }

        view.btnFindPlace.setOnClickListener {
            presenter.onProgress(edtFindPlace.text.toString())
        }

        view.btnAddPlace.setOnClickListener {
            var sendData: Intent = Intent(context, InsertWisataActivity::class.java)
            this.context?.startActivity(sendData)
        }

        return view;
    }

    override fun hideprogressbar(view: View) {
        view.progress_utama.visibility = View.GONE
    }

    override fun setAdapter(places: List<PlaceModel>, view: View) {
        view.rvItemPlace.visibility = View.VISIBLE
        var adapter = PlaceAdapter(places)
        view.rvItemPlace.adapter = adapter
    }

    override fun setviewprogress(view: View) {
        view.progress_utama.visibility = View.VISIBLE
        view.rvItemPlace.visibility = View.GONE
        view.error_layout.visibility = View.GONE
    }

    override fun showError(error: String?, view: View) {
        view.error_layout.visibility = View.VISIBLE
        view.error_txt_cause.text = error
        view.error_btn_retry.setOnClickListener {
            val presenter = ListLocationPresenter(this, view)
            presenter.onProgress(view.edtFindPlace.text.toString())
        }
    }
}
