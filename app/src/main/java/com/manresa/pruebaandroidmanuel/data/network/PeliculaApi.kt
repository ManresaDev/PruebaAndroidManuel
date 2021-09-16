package com.manresa.pruebaandroidmanuel.data.network

import com.manresa.pruebaandroidmanuel.data.model.Pelicula
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PeliculaApi {
    @POST("Play.php")
    @FormUrlEncoded
    suspend fun getPelicula(
        @Field("token") token : String,
        @Field("device") device : String,
        @Field("id") id : String
    ) : Response<Pelicula>
}