package com.manresa.pruebaandroidmanuel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.manresa.pruebaandroidmanuel.`interface`.LoginResultCallBacks
import com.manresa.pruebaandroidmanuel.databinding.ActivityLoginBinding
import com.manresa.pruebaandroidmanuel.ui.view.viewmodel.LoginViewModel
import com.manresa.pruebaandroidmanuel.ui.view.viewmodel.LoginViewModelFactory
import com.manresa.pruebaandroidmanuel.utils.Desencrypt

class LoginActivity : AppCompatActivity(), LoginResultCallBacks {
    private lateinit var binding : ActivityLoginBinding
    private val viewmodel : LoginViewModel by viewModels{LoginViewModelFactory(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        hide()
        loginClicked()
    }

    private fun hide() {
        supportActionBar?.hide()
    }

    private fun loginClicked() {
        binding.login.setOnClickListener {
            viewmodel.onLoginClicked(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    override fun onSuccess(message: String) {
        viewmodel.login(this)
    }

    override fun onError(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}