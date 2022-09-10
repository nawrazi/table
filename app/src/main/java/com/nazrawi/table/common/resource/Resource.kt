package com.nazrawi.table.common.resource

sealed class Resource<T>(val value: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, value: T? = null) : Resource<T>(value, message) {
        val errMsg: String
            get() = super.message ?: "Unknown Error"
    }

    class Loading<T> : Resource<T>()
}