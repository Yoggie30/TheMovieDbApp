package com.saiyoggie.themoviedbapp.data.remote.network


import com.saiyoggie.themoviedbapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getAPIService(): APIService {
        return retrofit.create(APIService::class.java)
    }
}