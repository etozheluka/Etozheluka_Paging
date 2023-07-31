package com.space.etozheluka_paging.data.remote.service

/**
 * This class is used to handle the API calls
 */
data class Resource<out T>(
    val status: Status = Status.LOADING,
    val data: T? = null,
    val message: String? = ""
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}