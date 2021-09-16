package com.manresa.pruebaandroidmanuel.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.manresa.pruebaandroidmanuel.PruebaAndroidManuel.Companion.prefs
import com.manresa.pruebaandroidmanuel.databinding.ActivityMainBinding
import com.manresa.pruebaandroidmanuel.ui.view.viewmodel.MainViewModel
import com.manresa.pruebaandroidmanuel.utils.ShowAlert

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var token : String
    private lateinit var device : String
    private val viewmodel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        signOutClicked()
        getToken()
        getDevice()
        getCartelera()
        hide()
    }

    private fun getDevice() {
        device = prefs.getDevice()
    }

    private fun getCartelera() {
      viewmodel.getCartelera(this, token, device).observe(this, Observer {
          Toast.makeText(this, it.user.name, Toast.LENGTH_SHORT).show()
      })
    }

    private fun getToken() {
        token = prefs.getToken()
        Toast.makeText(this, "$token", Toast.LENGTH_SHORT).show()
    }

    private fun signOutClicked() {
        binding.signOut.setOnClickListener {
                prefs.wipe()
                launchLogin()
        }
    }

    private fun launchLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun hide() {
        supportActionBar?.hide()
    }


}