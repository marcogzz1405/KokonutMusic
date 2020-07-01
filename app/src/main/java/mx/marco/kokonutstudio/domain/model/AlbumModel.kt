package mx.marco.kokonutstudio.domain.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(
    val id : Int,
    val title : String,
    val cover : String,
    val coverSmall : String,
    val coverMedium : String,
    val coverBig : String,
    val tracklist : String,
    val type : String
)