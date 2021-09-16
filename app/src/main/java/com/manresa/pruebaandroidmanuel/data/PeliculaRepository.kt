package com.manresa.pruebaandroidmanuel.data

import com.manresa.pruebaandroidmanuel.data.model.Pelicula
import com.manresa.pruebaandroidmanuel.data.model.PeliculaProvider
import com.manresa.pruebaandroidmanuel.data.network.PeliculaService

class PeliculaRepository {
    private val api = PeliculaService()

    suspend fun getPelicula (token : String, device : String, id : String) : Pelicula{
        val response = api.getPelicula(token, device, id)
        PeliculaProvider.pelicula = response
        return response
    }
}