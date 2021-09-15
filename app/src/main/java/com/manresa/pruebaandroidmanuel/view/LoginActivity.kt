package com.manresa.pruebaandroidmanuel.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.manresa.pruebaandroidmanuel.`interface`.LoginResultCallBacks
import com.manresa.pruebaandroidmanuel.databinding.ActivityLoginBinding
import com.manresa.pruebaandroidmanuel.viewmodel.LoginViewModel
import com.manresa.pruebaandroidmanuel.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity(), LoginResultCallBacks {
    private lateinit var binding : ActivityLoginBinding
    private val viewmodel : LoginViewModel by viewModels{LoginViewModelFactory(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loginClicked()
    }

    private fun loginClicked() {
        binding.login.setOnClickListener {
            viewmodel.onLoginClicked(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        //Aqui toda la lógica de encriptado
    }

    override fun onError(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}