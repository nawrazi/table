package com.nazrawi.table.common.interceptors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nazrawi.table.common.exceptions.APIException
import com.nazrawi.table.data.remote.model.TableResponse
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.reflect.Type

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val result = chain.proceed(builder.build())

        if (!result.isSuccessful) {
            val responseType: Type = object : TypeToken<TableResponse>() {}.type
            val serializedResponse: TableResponse? =
                Gson().fromJson(result.body?.string(), responseType)

            if (serializedResponse?.status == null) {
                throw APIException("Unknown Error")
            }

            if (result.code == 500) {
                throw APIException("Server Error")
            }
        }

        return result
    }
}