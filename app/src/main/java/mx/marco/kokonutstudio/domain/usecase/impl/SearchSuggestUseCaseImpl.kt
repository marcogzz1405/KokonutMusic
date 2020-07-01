package mx.marco.kokonutstudio.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import mx.marco.kokonutstudio.data.repository.ErrorModel
import mx.marco.kokonutstudio.data.repository.SearchRepository
import mx.marco.kokonutstudio.domain.SearchSuggestUseCase
import mx.marco.kokonutstudio.domain.model.SearchModel
import mx.marco.kokonutstudio.utils.Resource
import javax.inject.Inject

class SearchSuggestUseCaseImpl
@Inject
constructor(val repository: SearchRepository) : SearchSuggestUseCase {

    private val keywordLiveData = MutableLiveData<String>()
    val searchLiveData : LiveData<Resource<List<SearchModel>, ErrorModel>>

    init {
        searchLiveData = Transformations.switchMap(keywordLiveData){
            if(it.length>2){
                repository.searchProducts(it)
            } else {
                MutableLiveData<Resource<List<SearchModel>, ErrorModel>>().apply{ postValue(Resource.success(
                    listOf()))}
            }
        }
    }

    override fun searchSuggest(keyword: String) {
        keywordLiveData.value = keyword
    }

}