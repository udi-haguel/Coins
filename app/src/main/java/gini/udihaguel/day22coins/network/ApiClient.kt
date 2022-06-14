package gini.udihaguel.day22coins.network

import gini.udihaguel.day22coins.models.ApiResponse
import retrofit2.Response

class ApiClient(
    private val coinService: CoinService
) {
    suspend fun getMovies(): SimpleResponse<ApiResponse> {
        return safeApiCall { coinService.getCurrency() }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}