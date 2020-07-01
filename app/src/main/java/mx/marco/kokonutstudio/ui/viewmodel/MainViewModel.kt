package mx.marco.kokonutstudio.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mx.marco.kokonutstudio.data.repository.ErrorModel
import mx.marco.kokonutstudio.data.repository.SearchRepository
import mx.marco.kokonutstudio.domain.SearchSuggestUseCase
import mx.marco.kokonutstudio.domain.model.SearchModel
import mx.marco.kokonutstudio.domain.usecase.impl.SearchSuggestUseCaseImpl
import mx.marco.kokonutstudio.utils.Resource
import javax.inject.Inject

class MainViewModel
@Inject
constructor(repository: SearchRepository) : ViewModel() {

    private val searchUseCase : SearchSuggestUseCase
    val searchLiveData : LiveData<Resource<List<SearchModel>, ErrorModel>>

    init {
        searchUseCase = SearchSuggestUseCaseImpl(repository)
        searchLiveData = searchUseCase.searchLiveData
    }

    fun searchSuggest(query: String) {
        searchUseCase.searchSuggest(query)
    }

}