package com.manresa.pruebaandroidmanuel.data

import com.manresa.pruebaandroidmanuel.data.model.LoginProvider
import com.manresa.pruebaandroidmanuel.data.model.User
import com.manresa.pruebaandroidmanuel.data.network.LoginService

class LoginRepository {

    private val api = LoginService()

    suspend fun getUser(email: String, pass : String, device : String) : User{
        val response = api.getUser(email, pass, device)
        LoginProvider.user = response
        return  response
    }
}