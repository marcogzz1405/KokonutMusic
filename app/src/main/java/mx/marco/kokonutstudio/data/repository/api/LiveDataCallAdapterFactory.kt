package mx.marco.kokonutstudio.data.repository.api

import androidx.lifecycle.LiveData
import mx.marco.kokonutstudio.data.remote.response.ApiResponse
import mx.marco.kokonutstudio.utils.RESOURCE_MUST_BE_PARAMETERIZED
import mx.marco.kokonutstudio.utils.TYPE_MUST_BE_A_RESOURCE
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException(TYPE_MUST_BE_A_RESOURCE)
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException(RESOURCE_MUST_BE_PARAMETERIZED)
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}