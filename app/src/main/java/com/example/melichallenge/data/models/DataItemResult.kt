package com.example.melichallenge.data.models

data class DataItemResult(
    val code: Int,
    val body: Body
)

data class Body(
    val id: String,
    val title: String,
    val price: Double,
    val available_quantity: Int,
    val sold_quantity: Int,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val pictures: List<Picture>,
    val accepts_mercadopago: Boolean,
    val attributes: List<Attribute>,
    val warranty: String,
    val error: String,
    val message: String
)

data class Attribute(
    val id: String? = null,
    val name: String? = null,
    val value_name: String? = null,
)

data class Picture(
    val id: String,
    val url: String,
    val secure_url: String
)