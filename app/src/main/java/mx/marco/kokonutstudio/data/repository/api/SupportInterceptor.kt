package mx.marco.kokonutstudio.data.repository.api

import mx.marco.tiendasneto.data.config.BaseConfig
import okhttp3.*

class SupportInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val newUrl = when {
            (BaseConfig.ALTERNATIVE_URL!=null) and (BaseConfig.USE_ALTERNATIVE_URL) -> {
                val url = HttpUrl.parse(BaseConfig.ALTERNATIVE_URL!!)?:request.url().newBuilder()
                        .build()
                request
                        .url()
                        .newBuilder()
                        .scheme(url.scheme())
                        .host(url.host())
                        .port(url.port())
                        .build()
            }
            else ->
                request.url().newBuilder()
                .build()
        }
        val requestBuilder = request?.newBuilder()
            ?.url(newUrl)
            ?.addHeader("Content-Type", "application/json")
            ?.addHeader("Accept", "application/json")

        request = if(BaseConfig.USE_AUTH) {
            requestBuilder?.header("Authorization", "${BaseConfig.TYPE_AUTH} ${BaseConfig.AUTH_VALUE}")
                    ?.build()
        } else {
            requestBuilder?.build()
        }
        return chain.proceed(request)
    }

}