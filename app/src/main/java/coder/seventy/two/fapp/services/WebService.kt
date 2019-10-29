package coder.seventy.two.fapp.services

import coder.seventy.two.fapp.models.Product
import coder.seventy.two.fapp.models.Token
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("apikey") apikey: String
    ): Call<Token>

    @GET("product/paginate/{id}")
    fun getProducts(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<List<Product>>

}