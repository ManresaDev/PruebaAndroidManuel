package com.manresa.pruebaandroidmanuel.domain

import com.manresa.pruebaandroidmanuel.data.PeliculaRepository
import com.manresa.pruebaandroidmanuel.data.model.Pelicula

class GetPeliculaUseCase(val token : String, val device : String, val id : String) {
    private val repository = PeliculaRepository()

    suspend operator fun invoke() : Pelicula = repository.getPelicula(token, device, id)
}