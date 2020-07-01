package mx.marco.kokonutstudio.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.marco.tiendasneto.data.config.BaseConfig
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

class ApiResponse<T> {

    @Expose
    @SerializedName("code")
    var code: Int = 0
    @Expose
    @SerializedName(value="body", alternate = ["data"])
    var body: T? = null
    @Expose
    @SerializedName(value="errorMessage", alternate = ["message"])
    var errorMessage: String? = null

    constructor(throwableError: Throwable) {
        code = 500
        body = null
        errorMessage = when (throwableError) {
            is SocketTimeoutException -> BaseConfig.ERROR_NO_CONNECTION
            is HttpException -> {
                code = throwableError.code()
                throwableError.response()?.errorBody()?.toString()?:throwableError.message()
            }
            is ConnectException -> BaseConfig.ERROR_NO_CONNECTION
            else -> throwableError.message
        }
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            body = null
            errorMessage = response.errorBody()?.string() ?: response.message()
        }
    }

    fun isSuccessful(): Boolean = code in 200..299
}