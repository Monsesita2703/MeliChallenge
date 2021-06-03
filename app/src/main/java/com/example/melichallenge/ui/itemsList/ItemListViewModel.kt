package com.example.melichallenge.ui.itemsList

import androidx.lifecycle.*
import com.example.melichallenge.data.models.DataSearchResult
import com.example.melichallenge.data.repository.ItemRepository
import com.example.melichallenge.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ItemListViewModel @AssistedInject constructor(
        private val itemRepository: ItemRepository,
        @Assisted private val itemToSearch: String
): ViewModel() {

    class Factory(
        private val assistedFactory: ItemListViewModelAssistedFactory,
        private val itemToSearch: String,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return assistedFactory.create(itemToSearch) as T
        }
    }

    private val _items = MutableLiveData<Resource<DataSearchResult>>()
    val items : LiveData<Resource<DataSearchResult>>
        get() = _items

    init {
        getItemsBySearch()
    }

    private fun getItemsBySearch() {
        viewModelScope.launch {
            _items.postValue(Resource.loading(null))
            itemRepository.getItemsSearchResult(itemToSearch).let {
                if (it.isSuccessful) {
                    _items.postValue(Resource.success(it.body()!!))
                } else {
                    _items.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}

@AssistedFactory
interface ItemListViewModelAssistedFactory {
    fun create(itemToSearch: String): ItemListViewModel
}