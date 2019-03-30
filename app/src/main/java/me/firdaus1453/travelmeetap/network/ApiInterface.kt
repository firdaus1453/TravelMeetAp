package me.firdaus1453.travelmeetap.network

import io.reactivex.Observable
import me.firdaus1453.travelmeetap.Model.ResponseWisata
import me.firdaus1453.travelmeetap.model.ResponseObjectWisata
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by firdaus1453 on 3/30/2019.
 */
interface ApiInterface {
    @GET("wisata")
    fun getPlaces(): Observable<ResponseWisata>

    @FormUrlEncoded
    @POST("wisata/")
    fun postPlace(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("description") description: String,
        @Field("address") address: String,
        @Field("district") district: String,
        @Field("operational_time") operational_time: String,
        @Field("numb_telp") numb_telp: String,
        @Field("price") price: String,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String
    ): Call<ResponseObjectWisata>

    @FormUrlEncoded
    @PUT("wisata/")
    fun editPlace(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("description") description: String,
        @Field("address") address: String,
        @Field("district") district: String,
        @Field("operational_time") operational_time: String,
        @Field("numb_telp") numb_telp: String,
        @Field("price") price: String,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
        @Field("id") id: String
    ): Call<ResponseObjectWisata>

}