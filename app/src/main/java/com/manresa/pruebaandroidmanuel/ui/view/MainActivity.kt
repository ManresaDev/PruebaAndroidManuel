package com.manresa.pruebaandroidmanuel.ui.view

import android.content.Context
import android.content.Intent
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manresa.pruebaandroidmanuel.PruebaAndroidManuel.Companion.prefs
import com.manresa.pruebaandroidmanuel.adapter.CarteleraAdapter
import com.manresa.pruebaandroidmanuel.data.model.Content
import com.manresa.pruebaandroidmanuel.databinding.ActivityMainBinding
import com.manresa.pruebaandroidmanuel.ui.view.viewmodel.MainViewModel
import com.manresa.pruebaandroidmanuel.utils.ShowAlert
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var token : String
    private lateinit var device : String
    private val viewmodel : MainViewModel by viewModels()

    companion object{
        lateinit var adapter : CarteleraAdapter
        lateinit var recyclerViewCartelera : RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        recyclerViewCartelera = binding.recyclerview

        adapter = CarteleraAdapter(this)
        recyclerViewCartelera.layoutManager = GridLayoutManager(this,3)
        recyclerViewCartelera.adapter = adapter

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
        binding.loading.visibility = View.VISIBLE
      viewmodel.getCartelera(this, token, device).observe(this, Observer {
          binding.titulo.text = it.user.name
          Picasso.with(this).load(it.user.avatar).into(binding.avatar);
        binding.loading.visibility = View.GONE
          adapter.setListData(it.contents as MutableList<Content>)
          adapter.notifyDataSetChanged()
      })
    }

    private fun getToken() {
        token = prefs.getToken()
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