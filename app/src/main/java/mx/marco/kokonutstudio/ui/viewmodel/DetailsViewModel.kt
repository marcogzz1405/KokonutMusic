package mx.marco.kokonutstudio.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mx.marco.kokonutstudio.data.remote.response.LyricsResponse
import mx.marco.kokonutstudio.data.repository.ErrorModel
import mx.marco.kokonutstudio.data.repository.SearchRepository
import mx.marco.kokonutstudio.domain.model.LyricsModel
import mx.marco.kokonutstudio.domain.usecase.LyricsSongUseCase
import mx.marco.kokonutstudio.domain.usecase.impl.LyricsSongUseCaseImpl
import mx.marco.kokonutstudio.utils.Resource
import javax.inject.Inject

class DetailsViewModel
@Inject
constructor(repository: SearchRepository) : ViewModel() {

    private val getLyricsUseCase : LyricsSongUseCase
    val getLyricsLiveData : LiveData<Resource<LyricsResponse, ErrorModel>>

    init {
        getLyricsUseCase = LyricsSongUseCaseImpl(repository)
        getLyricsLiveData = getLyricsUseCase.getLyricsLiveData
    }

    fun getLyrics(artist: String, name: String){
        //val song = name.replace(" ", "%20")
        getLyricsUseCase.getLyrics(artist, name)
    }

}