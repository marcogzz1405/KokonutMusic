package mx.marco.kokonutstudio.data.remote.response

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("cover") val cover : String,
    @SerializedName("cover_small") val coverSmall : String,
    @SerializedName("cover_medium") val coverMedium : String,
    @SerializedName("cover_big") val coverBig : String,
    @SerializedName("tracklist") val tracklist : String,
    @SerializedName("type") val type : String
)