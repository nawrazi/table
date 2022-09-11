package com.nazrawi.table.common.resource

import com.nazrawi.table.common.exceptions.APIException
import com.nazrawi.table.common.exceptions.NetworkException

inline fun <R> buildResource(block: () -> R): Resource<R> {
    return try {
        Resource.Success(block())
    } catch (e: NetworkException) {
        Resource.Error(e.message)
    } catch (e: APIException) {
        Resource.Error(e.message)
    } catch (e: Exception) {
        Resource.Error("Unknown Error")
    }
}