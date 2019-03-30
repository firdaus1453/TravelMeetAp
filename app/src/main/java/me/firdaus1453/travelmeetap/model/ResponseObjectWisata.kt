package me.firdaus1453.travelmeetap.model

import me.firdaus1453.travelmeetap.Model.PlaceModel

/**
 * Created by firdaus1453 on 3/31/2019.
 */
data class ResponseObjectWisata (
    var status: Boolean? = null,
    var message: String? = null,
    var data: PlaceModel
)