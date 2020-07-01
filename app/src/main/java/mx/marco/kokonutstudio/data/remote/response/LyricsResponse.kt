package mx.marco.kokonutstudio.data.remote.response

import com.google.gson.annotations.SerializedName

data class LyricsResponse(
    @SerializedName("lyrics") val lyrics : String
)