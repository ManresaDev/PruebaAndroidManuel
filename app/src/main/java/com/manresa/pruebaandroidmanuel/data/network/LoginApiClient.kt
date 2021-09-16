package com.manresa.pruebaandroidmanuel.data.network

import com.manresa.pruebaandroidmanuel.data.model.User
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface LoginApiClient {
    @POST("Login.php")
    @FormUrlEncoded
    suspend fun getUser(
        @Field("user") user: String,
        @Field("pass") pass: String,
        @Field("device") device: String
    ):Response<User>
}