package mx.marco.kokonutstudio.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import mx.marco.kokonutstudio.data.remote.request.LyricsRequest
import mx.marco.kokonutstudio.data.remote.response.LyricsResponse
import mx.marco.kokonutstudio.data.repository.ErrorModel
import mx.marco.kokonutstudio.data.repository.SearchRepository
import mx.marco.kokonutstudio.domain.model.LyricsModel
import mx.marco.kokonutstudio.domain.usecase.LyricsSongUseCase
import mx.marco.kokonutstudio.utils.Resource
import javax.inject.Inject

class LyricsSongUseCaseImpl
@Inject
constructor(val repository: SearchRepository): LyricsSongUseCase {

    private val performRequestLiveData = MutableLiveData<LyricsRequest>()
    var getLyricsLiveData : LiveData<Resource<LyricsResponse, ErrorModel>>

    init {
        getLyricsLiveData = Transformations.switchMap(performRequestLiveData){
            repository.getLyrics(it)
        }
    }

    override fun getLyrics(artist: String, name: String) {
        performRequestLiveData.value = LyricsRequest(
            artist,
            name
        )
    }

}