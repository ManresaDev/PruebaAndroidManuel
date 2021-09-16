package com.manresa.pruebaandroidmanuel.data.network

import com.manresa.pruebaandroidmanuel.core.RetrofitHekper
import com.manresa.pruebaandroidmanuel.data.model.Pelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeliculaService {
    private val retrofit = RetrofitHekper.getRetrofit()

    suspend fun getPelicula(token : String, device : String, id : String) : Pelicula{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeliculaApi::class.java).getPelicula(token, device, id)
            response.body() ?: Pelicula("", "","", "", 0, "", 0, 0)
        }
    }
}