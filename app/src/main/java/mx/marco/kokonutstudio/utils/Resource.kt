package mx.marco.kokonutstudio.utils


class Resource<T, E> private constructor(val status: Status, val data: T?, val message: E?) {
    companion object {

        fun <T, E> success(data: T?): Resource<T, E> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T, E> error(error : E?): Resource<T, E> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T, E> loading(data: T?): Resource<T, E> {
            return Resource(Status.LOADING, data, null)
        }
    }
}