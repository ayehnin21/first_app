package coder.seventy.two.fapp.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://devmyanmar.com/api/"

    val Okhttp: OkHttpClient.Builder = OkHttpClient.Builder()

    val builder: Retrofit.Builder =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(Okhttp.build())

    val retrofit: Retrofit = builder.build()


    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}