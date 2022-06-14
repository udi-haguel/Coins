package gini.udihaguel.day22coins.network

import gini.udihaguel.day22coins.BuildConfig
import gini.udihaguel.day22coins.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkLayer {
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        //.client(client)
        //.baseUrl("http://data.fixer.io/api/")
        .baseUrl("https://pastebin.com/raw/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val coinService: CoinService by lazy {
        retrofit.create(CoinService::class.java)
    }

    val apiClient = ApiClient(coinService)
}


class TokenInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url().newBuilder().addQueryParameter("access_key", API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
    companion object {
        private const val API_KEY:String = BuildConfig.API_KEY
    }
}