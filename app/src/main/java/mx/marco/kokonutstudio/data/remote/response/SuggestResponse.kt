package mx.marco.kokonutstudio.data.remote.response

import com.google.gson.annotations.SerializedName

data class SuggestResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("readable") val readable : Boolean,
    @SerializedName("title") val title : String,
    @SerializedName("title_short") val titleShort : String,
    @SerializedName("link") val link : String,
    @SerializedName("duration") val duration : Int,
    @SerializedName("rank") val rank : Int,
    @SerializedName("preview") val preview : String,
    @SerializedName("artist") val artist : ArtistResponse,
    @SerializedName("album") val album : AlbumResponse,
    @SerializedName("type") val type : String
)