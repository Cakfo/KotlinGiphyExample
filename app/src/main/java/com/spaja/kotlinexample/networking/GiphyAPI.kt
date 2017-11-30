package com.spaja.kotlinexample.networking

import com.spaja.kotlinexample.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Spaja on 27-Nov-17.
 */
interface GiphyAPI {

    @GET("/v1/gifs/search")
    fun getGifs(@Query("api_key") apiKey: String, @Query("q") query: String): Call<ApiResponse>

    companion object {
        fun create(): GiphyAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.giphy.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(GiphyAPI::class.java)
        }
    }
}