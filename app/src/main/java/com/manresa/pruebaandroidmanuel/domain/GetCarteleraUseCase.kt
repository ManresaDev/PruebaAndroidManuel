package com.manresa.pruebaandroidmanuel.domain

import com.manresa.pruebaandroidmanuel.data.CarteleraRepository
import com.manresa.pruebaandroidmanuel.data.model.UserLoad

class GetCarteleraUseCase(val token: String, val device : String) {
    private val respository = CarteleraRepository()

    suspend operator fun invoke() : UserLoad = respository.getCartelera(token, device)
}