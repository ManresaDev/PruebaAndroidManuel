package com.manresa.pruebaandroidmanuel.data.network

import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CarteleraApiClient {
    @POST("GetView.php")
    @FormUrlEncoded
    suspend fun getCartelera(
        @Field("token") token : String,
        @Field("device") device : String
    ) :Response<UserLoad>
}