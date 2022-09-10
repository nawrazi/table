package com.nazrawi.table.data.remote.api

import com.nazrawi.table.common.Constants
import com.nazrawi.table.data.remote.model.TableDto
import retrofit2.Response
import retrofit2.http.GET

interface TableService {

    @GET("standings?league=39&season=${Constants.SEASON}")
    suspend fun getTable(): Response<TableDto>

}