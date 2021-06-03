package com.example.melichallenge.data.repository

import com.example.melichallenge.data.remote.ItemRemoteDataSource
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemRepositoryTest {

    @Mock
    private lateinit var itemRemoteDataSource: ItemRemoteDataSource

    private lateinit var itemRepository: ItemRepository

    @Before
    fun setUp() {
        itemRepository = ItemRepository(itemRemoteDataSource)
    }

    @Test
    fun getItemSearchResultNull() {

        runBlocking {
            doReturn(null)
                .`when`(itemRemoteDataSource)
                .getItemsSearchResult("After")
            val dataSearchResult = itemRepository.getItemsSearchResult("After")
            assertEquals(
                dataSearchResult, null
            )
        }
    }

    @Test
    fun getItemDetailResultNull() {

        runBlocking {
            doReturn(null)
                .`when`(itemRemoteDataSource)
                .getItemDetailResult("MLA909372335")
            val dataItemDetailResult = itemRepository.getItemDetailResult("MLA909372335")
            assertEquals(
                dataItemDetailResult, null
            )
        }
    }
}