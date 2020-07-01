package mx.marco.kokonutstudio.data.repository

import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import mx.marco.kokonutstudio.data.remote.response.ApiResponse
import mx.marco.kokonutstudio.utils.ERROR_SERVICE_RESPONSE
import mx.marco.kokonutstudio.utils.Resource


abstract class ProcessedNetworkResource<R, C, E> {

    private val result: MediatorLiveData<Resource<C, E>> = MediatorLiveData()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponseLiveData = createCall()
        setValue(Resource.loading(null))
        result.addSource(apiResponseLiveData) {
            result.removeSource(apiResponseLiveData)
            if (it != null) {
                if (it.isSuccessful()) {
                    setValue(Resource.success(processResponse(it.body)))
                } else {

                    setValue(Resource.error(mapError(it.errorMessage!!)))
                }
            } else {
                setValue(Resource.error(mapError(ERROR_SERVICE_RESPONSE)))
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<C, E>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    fun asLiveData(): LiveData<Resource<C, E>> = result

    @NonNull
    @MainThread
    abstract fun createCall(): LiveData<ApiResponse<R>>

    @WorkerThread
    abstract fun processResponse(response: R?): C?

    @WorkerThread
    abstract fun mapError(body: String) : E?
}