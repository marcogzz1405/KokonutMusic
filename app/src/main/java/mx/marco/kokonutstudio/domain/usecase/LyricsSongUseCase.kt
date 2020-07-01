package mx.marco.kokonutstudio.domain.usecase

interface LyricsSongUseCase {
    fun getLyrics(artist: String, name: String)
}