package com.nazrawi.table.common.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline isEmpty: (ResultType) -> Boolean = { false },
) = flow {
    emit(Resource.Loading())
    val data = query().first()

    if (!isEmpty(data))
        emit(Resource.Success(data))

    val fetched = buildResource { fetch() }

    val response = if (fetched is Resource.Success) {
        saveFetchResult(fetched.value!!)
        query().map { res -> Resource.Success(res) }
    } else {
        query().map { res -> Resource.Error(fetched.message, res) }
    }

    emit(response.first())
}