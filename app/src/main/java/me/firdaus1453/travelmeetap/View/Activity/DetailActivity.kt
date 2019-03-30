package me.firdaus1453.travelmeetap.View.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import me.firdaus1453.travelmeetap.Model.PlaceModel
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.mvp.contract.DetailContract
import me.firdaus1453.travelmeetap.mvp.presenter.DetailPresenter
import java.text.NumberFormat

class DetailActivity : AppCompatActivity(), DetailContract.View {
    lateinit var placeModel: PlaceModel
    val presenter = DetailPresenter(this, this)

    companion object {
        val ExtraData: String = "extradata"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.setDetail()

        btnEditDetail.setOnClickListener {
            var sendData: Intent = Intent(this, InsertWisataActivity::class.java)
            sendData.putExtra(DetailActivity.ExtraData, placeModel)
            startActivity(sendData)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setDetail()
    }

    @SuppressLint("SetTextI18n")
    override fun detailPlace(
        name: TextView,
        price: TextView,
        tlpDetail: TextView,
        district: TextView,
        address: TextView,
        desc: TextView,
        spOperational: Spinner,
        img: ImageView
    ) {
        placeModel = intent.getParcelableExtra(ExtraData)
        if (placeModel.image.isNotEmpty()) {
            Picasso.with(this).load(placeModel.image).into(img)
        }
        name.text = placeModel.name
        price.text = "Rp. " + NumberFormat.getInstance().format(placeModel.price)
        tlpDetail.text = if (placeModel.numb_telp == null) "-" else placeModel.numb_telp
        district.text = placeModel.district
        address.text = placeModel.address
        desc.text = placeModel.description
        Log.i("Cek", "Pcle : " + placeModel.operational_time)

        var mOperationalTime = arrayOf(placeModel.operational_time)

        spOperational.adapter =
                ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    mOperationalTime
                )
        spOperational.setSelection(0)
    }
}
