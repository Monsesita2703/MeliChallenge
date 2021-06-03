package com.example.melichallenge.data.remote

import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(
    private val itemService: ItemService
) {
    suspend fun getItemsSearchResult(item: String) = itemService.getItemsSearchResult(item)
    suspend fun getItemDetailResult(itemId: String) = itemService.getItemDetailResult(itemId)
}