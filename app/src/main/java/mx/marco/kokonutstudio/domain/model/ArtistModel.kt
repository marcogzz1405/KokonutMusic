package mx.marco.kokonutstudio.domain.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    val id : Int,
    val name : String,
    val link : String,
    val picture : String,
    val pictureSmall : String,
    val pictureMedium : String,
    val pictureBig : String,
    val pictureXl : String,
    val tracklist : String,
    val type : String
)