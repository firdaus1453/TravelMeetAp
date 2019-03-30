package me.firdaus1453.travelmeetap.Model

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PlaceModel() : Parcelable {
    var id: Int = 0
    var image: String = ""
    var name: String = ""
    var description: String = ""
    var distance: Int = 0
    var address: String = ""
    var district: String = ""
    var operational_time: String = ""
    var numb_telp: String? = ""
    var price: Int = 0
    var latitude: String = ""
    var longitude: String = ""
    var latlong: String = ""

    constructor(id: Int, image: String, name: String,
                desc: String, distance: Int, address: String,
                district:String,operationalTime:String,
                numbTelp:String?,price:Int,lat:String,long: String,latlong:String) : this(){
        this.id = id
        this.image = image
        this.name = name
        this.description = desc
        this.distance = distance
        this.address = address
        this.district = district
        this.operational_time = operationalTime
        this.numb_telp = numbTelp
        this.price = price
        this.latitude = lat
        this.longitude = long
        this.latlong = latlong
    }
    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        image = parcel.readString()
        name = parcel.readString()
        description = parcel.readString()
        distance = parcel.readInt()
        address = parcel.readString()
        district = parcel.readString()
        operational_time = parcel.readString()
        numb_telp = parcel.readString()
        price = parcel.readInt()
        latitude = parcel.readString()
        longitude = parcel.readString()
        latlong = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(distance)
        parcel.writeString(address)
        parcel.writeString(district)
        parcel.writeString(operational_time)
        parcel.writeString(numb_telp)
        parcel.writeInt(price)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(latlong)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceModel> {
        override fun createFromParcel(parcel: Parcel): PlaceModel {
            return PlaceModel(parcel)
        }

        override fun newArray(size: Int): Array<PlaceModel?> {
            return arrayOfNulls(size)
        }
    }

}