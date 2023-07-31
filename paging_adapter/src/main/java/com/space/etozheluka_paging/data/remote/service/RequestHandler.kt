package com.space.etozheluka_paging.data.remote.service

import retrofit2.Response

/**
 * This class is used to handle the API calls
 */
abstract class RequestHandler {
    suspend fun <T> apiCall(request: suspend () -> Response<T>): Resource<T> {
        return try {
            Resource.loading(null)
            val response = request()
            if (response.isSuccessful) {
                Resource.success(response.body()!!)
            } else {
                Resource.error(response.message())
            }
        } catch (e: Exception) {
            Resource.error(e.message ?: "error")
        }
    }
}