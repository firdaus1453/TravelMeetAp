package me.firdaus1453.travelmeetap.View.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_insert_wisata.*
import me.firdaus1453.travelmeetap.Model.PlaceModel
import me.firdaus1453.travelmeetap.Model.ResponseWisata
import me.firdaus1453.travelmeetap.R
import me.firdaus1453.travelmeetap.model.ResponseObjectWisata
import me.firdaus1453.travelmeetap.network.ApiInterface
import me.firdaus1453.travelmeetap.utils.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertWisataActivity : AppCompatActivity() {
    var placeModel: PlaceModel? = null
    var mApiInterface: ApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_wisata)

        val intent:Intent = getIntent()
        if (intent.extras != null){
            placeModel = intent.getParcelableExtra(DetailActivity.ExtraData)
            if (placeModel != null) {
                setData()
            }
        }


        btn_Simpan.setOnClickListener {
            if (txtNama.text.toString() == "") run {
                Toast.makeText(applicationContext, "Nama Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtDeskripsi.text.toString() == "") run {
                Toast.makeText(applicationContext, "Deskripsi Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtAlamat.text.toString() == "") run {
                Toast.makeText(applicationContext, "Alamat Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtKecamatan.text.toString() == "") run {
                Toast.makeText(applicationContext, "Kecamatan Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtNotelp.text.toString() == "") run {
                Toast.makeText(applicationContext, "Notelp Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtHarga.text.toString() == "") run {
                Toast.makeText(applicationContext, "Harga Wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (txtJarak.text.toString() == "") run {
                Toast.makeText(applicationContext, "Jarak Wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                if (placeModel == null) {
                    addPlace()
//                    Toast.makeText(applicationContext, "Berhasil Tambah Wisata", Toast.LENGTH_SHORT).show()
                } else {
                    editPlace()
                }
            }
        }

    }

    private fun setData() {
        txtNama.setText(placeModel?.name)
        txtDeskripsi.setText(placeModel?.description)
        txtAlamat.setText(placeModel?.address)
        txtKecamatan.setText(placeModel?.district)
        txtOperationalTime.setText(placeModel?.operational_time)
        txtNotelp.setText(placeModel?.numb_telp)
        txtHarga.setText(placeModel?.price.toString())
        txtLatitude.setText(placeModel?.latitude)
        txtLongitude.setText(placeModel?.longitude)
    }

    private fun editPlace() {
        mApiInterface = ApiUtils.apiInterface
        mApiInterface!!.editPlace(
            txtNama.text.toString(),
            "",
            txtDeskripsi.text.toString(),
            txtAlamat.text.toString(),
            txtKecamatan.text.toString(),
            txtOperationalTime.text.toString(),
            txtNotelp.text.toString(),
            txtHarga.text.toString(),
            txtLatitude.text.toString(),
            txtLongitude.text.toString(),
            placeModel?.id.toString()
        ).enqueue(object : Callback<ResponseObjectWisata> {
            override fun onResponse(call: Call<ResponseObjectWisata>, response: Response<ResponseObjectWisata>) {
                if (response.isSuccessful()) {
                    if (!response.body()!!.message.isNullOrEmpty()) {
                        Toast.makeText(this@InsertWisataActivity, response.body()!!.message, LENGTH_SHORT).show()
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseObjectWisata>, t: Throwable) {
                Toast.makeText(this@InsertWisataActivity, t.message, LENGTH_SHORT).show()
                Log.i("Cek", t.message)
            }

        })
    }

    fun addPlace() {
        mApiInterface = ApiUtils.apiInterface
        mApiInterface!!.postPlace(
            txtNama.text.toString(),
            "",
            txtDeskripsi.text.toString(),
            txtAlamat.text.toString(),
            txtKecamatan.text.toString(),
            txtOperationalTime.text.toString(),
            txtNotelp.text.toString(),
            txtHarga.text.toString(),
            txtLatitude.text.toString(),
            txtLongitude.text.toString()
        ).enqueue(object : Callback<ResponseObjectWisata> {

            override fun onResponse(call: Call<ResponseObjectWisata>, response: Response<ResponseObjectWisata>) {

                if (response.isSuccessful()) {
                    if (!response.body()!!.message.isNullOrEmpty()) {
                        Toast.makeText(this@InsertWisataActivity, response.body()!!.message, LENGTH_SHORT).show()
                        //                        intentToList()

                        val intent = Intent(this@InsertWisataActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseObjectWisata>, t: Throwable) {
                Toast.makeText(this@InsertWisataActivity, t.message, LENGTH_SHORT).show()
                Log.i("Cek", t.message)
            }
        })
//        Log.e("post", "simpan klik")
    }
}
