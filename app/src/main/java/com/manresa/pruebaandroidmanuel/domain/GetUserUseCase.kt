package com.manresa.pruebaandroidmanuel.domain

import com.manresa.pruebaandroidmanuel.data.LoginRepository
import com.manresa.pruebaandroidmanuel.data.model.User

class GetUserUseCase(val email : String, val pass: String, val device : String) {
    private val repository = LoginRepository()

    suspend operator fun invoke() : User? = repository.getUser(email, pass, device)

}