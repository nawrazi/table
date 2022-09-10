package com.nazrawi.table.common.resource

import com.nazrawi.table.common.exceptions.APIException
import com.nazrawi.table.common.exceptions.NetworkException

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