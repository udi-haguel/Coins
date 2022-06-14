package gini.udihaguel.day22coins.network

import gini.udihaguel.day22coins.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoinService {

    @GET("NGHJ3UTW" /*"latest"*/)
    suspend fun getCurrency(): Response<ApiResponse>
}