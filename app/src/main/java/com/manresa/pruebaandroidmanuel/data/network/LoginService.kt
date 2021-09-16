package com.manresa.pruebaandroidmanuel.data.network

import android.provider.MediaStore
import com.manresa.pruebaandroidmanuel.core.RetrofitHekper
import com.manresa.pruebaandroidmanuel.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody




class LoginService {
    private val retrofit = RetrofitHekper.getRetrofit()

    suspend fun getUser(email: String, pass : String, device : String) : User{
        return withContext(Dispatchers.IO){


           /* val emailFormat = RequestBody.create(MediaType.parse("text/plain"), email)


            val passFormat = RequestBody.create(MediaType.parse("text/plain"), pass)

            val deviceFormat = RequestBody.create(MediaType.parse("text/plain"), device) */


            val response = retrofit.create(LoginApiClient::class.java).getUser(email, pass, device)
            response.body() ?: User(true, "Parametros incorrectos", false, "")
        }

    }
}