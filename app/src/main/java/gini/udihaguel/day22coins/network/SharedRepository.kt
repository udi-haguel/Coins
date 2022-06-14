package gini.udihaguel.day22coins.network


import gini.udihaguel.day22coins.models.ApiResponse

class SharedRepository {

    suspend fun getCurrency(): ApiResponse {
        val request = NetworkLayer.apiClient.getMovies()

        if (request.failed){
            println("no internet")
        }

        if (!request.isSuccessful) {
            println("not authorized")
        }

        return request.body
    }
}
