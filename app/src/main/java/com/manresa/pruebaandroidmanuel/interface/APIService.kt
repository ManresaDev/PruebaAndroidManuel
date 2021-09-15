package com.manresa.pruebaandroidmanuel.`interface`

import com.manresa.pruebaandroidmanuel.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @POST("login/{user}/{pass}/{device}")
    fun getUserByEmailAndPassword(@Path("user") user : String, @Path("pass") pass : String, @Path("device") device : String):Response<User>
}