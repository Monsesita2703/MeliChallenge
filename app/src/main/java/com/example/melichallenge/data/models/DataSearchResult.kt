package com.example.melichallenge.data.models

data class DataSearchResult(
    val paging: Paging,
    val results: List<Item>
)

data class Paging (
    val total: Int? = null,
    val primary_results: Int? = null,
    val offset: Int? = null,
    val limit: Int? = null
)

data class Item(
    val id: String,
    val title: String,
    val price: Double,
    val sale_price: Double,
    val condition: String,
    val thumbnail: String,
    val shipping: Shipping
)

data class Shipping(
    val free_shipping: Boolean
)