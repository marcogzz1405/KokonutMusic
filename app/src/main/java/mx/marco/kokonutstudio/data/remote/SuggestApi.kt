package mx.marco.kokonutstudio.data.remote

import androidx.lifecycle.LiveData
import mx.marco.kokonutstudio.data.remote.response.ApiResponse
import mx.marco.kokonutstudio.data.remote.response.GeneralResponse
import mx.marco.kokonutstudio.data.remote.response.LyricsResponse
import mx.marco.kokonutstudio.data.remote.response.SuggestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SuggestApi {

    @GET("suggest/{search_term}")
    fun getSuggest(@Path("search_term") search_term: String) : LiveData<ApiResponse<GeneralResponse<List<SuggestResponse>>>>

    @GET("v1/{artist}/{song}")
    fun getLyrics(@Path("artist") artist: String,
                  @Path("song") song: String) : LiveData<ApiResponse<LyricsResponse>>

}