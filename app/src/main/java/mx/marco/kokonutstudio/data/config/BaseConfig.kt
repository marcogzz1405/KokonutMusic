package mx.marco.tiendasneto.data.config

class BaseConfig {
    companion object {
        var BASE_URL : String = "https://api.lyrics.ovh/"
        var USE_AUTH : Boolean = false
        var TYPE_AUTH : String = "Bearer"
        var AUTH_VALUE : String = ""
        var ERROR_NO_CONNECTION = "Ocurrió un error, revisa tu conexión a internet"
        var ALTERNATIVE_URL : String? = null
        var USE_ALTERNATIVE_URL : Boolean = false
        var ENABLE_SSL_VALIDATION : Boolean = true
        var PREFERENCE_LABEL : String = "KokonutStudio"
    }
}