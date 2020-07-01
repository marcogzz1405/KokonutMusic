package mx.marco.kokonutstudio.data.remote.response

data class GeneralResponse<T> (var code: Int, var data: T)