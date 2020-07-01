package mx.marco.kokonutstudio.data.repository

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import mx.marco.kokonutstudio.data.remote.SuggestApi
import mx.marco.kokonutstudio.data.remote.request.LyricsRequest
import mx.marco.kokonutstudio.data.remote.response.*
import mx.marco.kokonutstudio.domain.model.AlbumModel
import mx.marco.kokonutstudio.domain.model.ArtistModel
import mx.marco.kokonutstudio.domain.model.LyricsModel
import mx.marco.kokonutstudio.domain.model.SearchModel
import mx.marco.kokonutstudio.utils.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository
@Inject constructor(private val api: SuggestApi) {

    fun searchProducts(keyword: String) : LiveData<Resource<List<SearchModel>, ErrorModel>> {
        return object : ProcessedNetworkResource<GeneralResponse<List<SuggestResponse>>,List<SearchModel>, ErrorModel>() {
            override fun createCall(): LiveData<ApiResponse<GeneralResponse<List<SuggestResponse>>>> {
                return api.getSuggest(keyword)
            }

            override fun mapError(body: String): ErrorModel? {
                return try {
                    Gson().fromJson(body, ErrorModel::class.java)
                } catch (ex: Exception) {
                    ErrorModel(500, body, "Error")
                }
            }

            override fun processResponse(response: GeneralResponse<List<SuggestResponse>>?): List<SearchModel>? {
                return response?.data?.map {
                    SearchModel(
                        it.id,
                        it.readable,
                        it.title,
                        it.titleShort,
                        it.link,
                        it.duration,
                        it.rank,
                        it.preview,
                        ArtistModel(
                            it.artist.id,
                            it.artist.name,
                            it.artist.link,
                            it.artist.picture,
                            it.artist.pictureSmall,
                            it.artist.pictureMedium,
                            it.artist.pictureBig,
                            it.artist.pictureXl,
                            it.artist.tracklist,
                            it.artist.type
                        ),
                        AlbumModel(
                            it.album.id,
                            it.album.title,
                            it.album.cover,
                            it.album.coverSmall,
                            it.album.coverMedium,
                            it.album.coverBig,
                            it.album.tracklist,
                            it.album.type
                        ),
                        it.type
                    )
                }
            }

        }.asLiveData()
    }

    fun getLyrics(request: LyricsRequest) : LiveData<Resource<LyricsResponse,ErrorModel>> {
        return object : ProcessedNetworkResource<LyricsResponse, LyricsResponse, ErrorModel>() {
            override fun createCall(): LiveData<ApiResponse<LyricsResponse>> {
                return api.getLyrics(request.artist, request.name)
            }

            override fun mapError(body: String): ErrorModel? {
                return try {
                    Gson().fromJson(body, ErrorModel::class.java)
                } catch (ex: Exception) {
                    ErrorModel(500, body, "Error")
                }
            }

            override fun processResponse(response: LyricsResponse?): LyricsResponse? {
                return LyricsResponse(
                    response?.lyrics?:""
                )
            }
        }.asLiveData()
    }

}