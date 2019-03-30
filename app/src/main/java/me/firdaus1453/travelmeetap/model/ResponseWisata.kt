package me.firdaus1453.travelmeetap.Model

data class ResponseWisata(
    var status: Boolean? = null,
    var message: String? = null,
	var data: List<PlaceModel>
)