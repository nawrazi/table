package com.nazrawi.table.data.remote.api

import com.nazrawi.table.data.remote.model.TableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TableService {

    @GET("table/{league}")
    suspend fun getTable(@Path("league") league: String): Response<TableResponse>

}