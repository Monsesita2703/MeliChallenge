package com.example.melichallenge.data.remote

import com.example.melichallenge.data.models.DataItemResult
import com.example.melichallenge.data.models.DataSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

    @GET("/sites/MLA/search?")
    suspend fun getItemsSearchResult(
        @Query("q") item: String
    ): Response<DataSearchResult>

    @GET("/items?")
    suspend fun getItemDetailResult(
        @Query("ids") itemId: String
    ): Response<List<DataItemResult>>
}