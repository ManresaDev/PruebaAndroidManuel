package com.manresa.pruebaandroidmanuel.data.network

import com.manresa.pruebaandroidmanuel.core.RetrofitHekper
import com.manresa.pruebaandroidmanuel.data.model.UserCartelera
import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarteleraService {
    private val retrofit = RetrofitHekper.getRetrofit()

    suspend fun getCartelera(token : String, device : String) : UserLoad{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CarteleraApiClient::class.java).getCartelera(token, device)
            response.body()!!
        }
    }
}