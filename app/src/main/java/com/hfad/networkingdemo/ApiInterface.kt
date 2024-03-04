package com.hfad.networkingdemo

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("marvel/")
    fun getHeros() : Call<ArrayList<Hero?>?>?

    companion object {
        var BASE_URL = "https://simplifiedcoding.net/demos/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}