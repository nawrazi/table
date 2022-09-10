package com.nazrawi.table.common

import com.nazrawi.table.common.exceptions.APIException
import com.nazrawi.table.common.exceptions.NetworkException

sealed class Resource<T>(val value: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, value: T? = null) : Resource<T>(value, message) {
        val errMsg: String
            get() = super.message ?: "Unknown Error"
    }

    class Loading<T> : Resource<T>()
}

/**
 * Build a resource object from the given block without explicitly writing a try catch block.
 */
inline fun <R> buildResource(block: () -> R): Resource<R> {
    return try {
        Resource.Success(block())
    } catch (e: NetworkException) {
        Resource.Error(e.message)
    } catch (e: APIException) {
        Resource.Error("Error Connecting to Server")
    } catch (e: Exception) {
        Resource.Error("Unknown Error")
    }
}