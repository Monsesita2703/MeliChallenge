package com.example.melichallenge.data.repository

import com.example.melichallenge.data.remote.ItemRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val remoteDataSource: ItemRemoteDataSource
) {
    suspend fun getItemsSearchResult(item: String) =
        remoteDataSource.getItemsSearchResult(item)

    suspend fun getItemDetailResult(itemId: String) =
        remoteDataSource.getItemDetailResult(itemId)
}