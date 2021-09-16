package com.manresa.pruebaandroidmanuel.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHekper {
    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dev.perseo.tv/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}