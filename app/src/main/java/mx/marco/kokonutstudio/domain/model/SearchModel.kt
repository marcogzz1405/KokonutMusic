package mx.marco.kokonutstudio.domain.model

data class SearchModel(
    val id: Int,
    val readable: Boolean,
    val title: String,
    val titleShort: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    val preview: String,
    val artist: ArtistModel?,
    val album: AlbumModel?,
    val type : String
)