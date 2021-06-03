package com.example.melichallenge.ui.searchItem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val searchViewModel by lazy { SearchViewModel() }

    @Test
    fun validateItemEmpty() {
        searchViewModel.itemToSearch.value = ""
        searchViewModel.validateItem()
        assertEquals(searchViewModel.isValidItem.value, false)
    }

    @Test
    fun validateItemNotEmpty() {
        searchViewModel.itemToSearch.value = "After"
        searchViewModel.validateItem()
        assertEquals(searchViewModel.isValidItem.value, true)
    }
}