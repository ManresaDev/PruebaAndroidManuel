package com.manresa.pruebaandroidmanuel.ui.view.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manresa.pruebaandroidmanuel.PruebaAndroidManuel.Companion.prefs
import com.manresa.pruebaandroidmanuel.`interface`.LoginResultCallBacks
import com.manresa.pruebaandroidmanuel.data.model.User
import com.manresa.pruebaandroidmanuel.domain.GetUserUseCase
import com.manresa.pruebaandroidmanuel.ui.view.MainActivity
import com.manresa.pruebaandroidmanuel.utils.CheckLoginData
import com.manresa.pruebaandroidmanuel.utils.Constante
import com.manresa.pruebaandroidmanuel.utils.Desencrypt
import kotlinx.coroutines.launch

class LoginViewModel(private val listener : LoginResultCallBacks) : ViewModel() {

    private lateinit var password: String
    private lateinit var email : String

    val user = MutableLiveData<User>()



     fun onLoginClicked(email: String, password : String){
         this.password = password
         this.email = email
        val isDataValid = CheckLoginData.isDataValid(email, password)
         when(isDataValid){
             0 -> listener.onError("Rellena todos los campos para acceder")
             1 -> listener.onError("El patrón de Email no es correcto")
             else -> listener.onSuccess("Acceso exitoso")
         }
    }

    fun login(activity : Activity){
        val hashMD5 = Desencrypt.desencryptMD5(password)
        var getUserUseCase = GetUserUseCase(email, hashMD5, Constante.ANDROID)
        viewModelScope.launch {
            val result = getUserUseCase()

            if (result!!.error){
                Toast.makeText(activity, "${result.message}", Toast.LENGTH_SHORT).show()
            }else{
                user.postValue(result!!)

                prefs.saveToken(result.token)
                prefs.saveDevice(Constante.ANDROID)
                launchMain(activity)
            }

        }
    }



    private fun launchMain(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }


}