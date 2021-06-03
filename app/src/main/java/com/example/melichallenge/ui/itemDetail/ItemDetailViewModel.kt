package com.example.melichallenge.ui.itemDetail

import androidx.lifecycle.*
import com.example.melichallenge.data.models.DataItemResult
import com.example.melichallenge.data.repository.ItemRepository
import com.example.melichallenge.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ItemDetailViewModel @AssistedInject constructor(
        private val itemRepository: ItemRepository,
        @Assisted private val itemId: String
): ViewModel() {

    class Factory(
            private val assistedFactory: ItemDetailViewModelAssistedFactory,
            private val itemId: String,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return assistedFactory.create(itemId) as T
        }
    }

    private val _itemDetail = MutableLiveData<Resource<List<DataItemResult>>>()
    val itemDetail : LiveData<Resource<List<DataItemResult>>>
        get() = _itemDetail

    init {
        getItemById()
    }

    private fun getItemById() {
        viewModelScope.launch {
            _itemDetail.postValue(Resource.loading(null))
            itemRepository.getItemDetailResult(itemId).let {
                if (it.isSuccessful) {
                    _itemDetail.postValue(Resource.success(it.body()!!))
                } else {
                    _itemDetail.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}

@AssistedFactory
interface ItemDetailViewModelAssistedFactory {
    fun create(itemToSearch: String): ItemDetailViewModel
}