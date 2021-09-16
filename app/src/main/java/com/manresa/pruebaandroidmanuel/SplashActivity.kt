package com.manresa.pruebaandroidmanuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manresa.pruebaandroidmanuel.PruebaAndroidManuel.Companion.prefs
import com.manresa.pruebaandroidmanuel.ui.view.LoginActivity
import com.manresa.pruebaandroidmanuel.ui.view.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        checkToken()
    }

    private fun checkToken() {
        if(prefs.getToken().isNotEmpty()){
            launchMain()
        }else{
            launchLogin()
        }
    }

    private fun launchLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}