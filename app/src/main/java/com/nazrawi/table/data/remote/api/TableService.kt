package com.nazrawi.table.data.remote.api

import com.nazrawi.table.common.Constants
import com.nazrawi.table.data.remote.model.TableDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TableService {

    @GET("standings?season=${Constants.SEASON}")
    suspend fun getTable(@Query("league") league: String): Response<TableDto>

}