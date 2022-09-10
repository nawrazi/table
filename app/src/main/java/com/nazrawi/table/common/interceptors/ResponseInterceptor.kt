package com.nazrawi.table.common.interceptors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nazrawi.table.common.exceptions.APIException
import com.nazrawi.table.data.remote.model.TableDto
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.reflect.Type

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val result = chain.proceed(builder.build())

        if (!result.isSuccessful) {
            val responseType: Type = object : TypeToken<TableDto>() {}.type
            val serializedResponse: TableDto =
                Gson().fromJson(result.body!!.string(), responseType)

            if (serializedResponse.results == 0)
                throw APIException()
        }

        return result
    }
}