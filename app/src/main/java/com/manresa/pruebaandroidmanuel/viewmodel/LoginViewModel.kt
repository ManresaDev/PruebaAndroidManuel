package com.manresa.pruebaandroidmanuel.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.manresa.pruebaandroidmanuel.`interface`.LoginResultCallBacks
import com.manresa.pruebaandroidmanuel.data.model.User
import com.manresa.pruebaandroidmanuel.utils.CheckLoginData
import com.manresa.pruebaandroidmanuel.utils.Desencrypt

class LoginViewModel(private val listener : LoginResultCallBacks) : ViewModel() {

    private lateinit var user : User
    private lateinit var password: String

     fun onLoginClicked(email: String, password : String){
         this.password = password
       val isDataValid = CheckLoginData.isDataValid(email, password)
         when(isDataValid){
             0 -> listener.onError("Rellena todos los campos para acceder")
             1 -> listener.onError("El patrón de Email no es correcto")
             else -> listener.onSuccess("Acceso exitoso")
         }
    }

    fun login(){
        val hashMD5 = Desencrypt.desencryptMD5(password)
        
    }



}