package me.firdaus1453.travelmeetap.utils

import me.firdaus1453.travelmeetap.network.ApiInterface
import me.firdaus1453.travelmeetap.network.RetrofitClient

/**
 * Created by firdaus1453 on 3/30/2019.
 */
object ApiUtils {
    val BASE_URL: String = "http://192.168.56.1"
    val apiInterface : ApiInterface get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
}