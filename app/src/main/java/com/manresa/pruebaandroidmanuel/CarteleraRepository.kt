package com.manresa.pruebaandroidmanuel

import com.manresa.pruebaandroidmanuel.data.model.CarteleraProvider
import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import com.manresa.pruebaandroidmanuel.data.network.CarteleraService

class CarteleraRepository {
    private val api = CarteleraService()

    suspend fun getCartelera(token : String, device : String) : UserLoad{
        val response = api.getCartelera(token, device)
        CarteleraProvider.userLoad = response
        return response
    }
}