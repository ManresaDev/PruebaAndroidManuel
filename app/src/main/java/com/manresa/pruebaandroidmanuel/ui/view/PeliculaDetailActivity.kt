package com.manresa.pruebaandroidmanuel.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.manresa.pruebaandroidmanuel.PruebaAndroidManuel.Companion.prefs
import com.manresa.pruebaandroidmanuel.data.model.Pelicula
import com.manresa.pruebaandroidmanuel.databinding.ActivityPeliculaDetailBinding
import com.manresa.pruebaandroidmanuel.ui.view.viewmodel.PeliculaViewModel
import com.manresa.pruebaandroidmanuel.utils.ConvertSeg
import com.squareup.picasso.Picasso

class PeliculaDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPeliculaDetailBinding
    private lateinit var id : String
    private lateinit var token : String
    private lateinit var device : String
    private val viewmodel : PeliculaViewModel by viewModels()
    private lateinit var peliculas : ArrayList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)


        getId()
        getToken()
        getDevice()
        getPelicula()
        backPressed()

        hide()
    }

    private fun backPressed() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun getPelicula() {
        viewmodel.getPelicula(token, device, id).observe(this, Observer {
            Picasso.with(this).load(it.cover).fit().into(binding.caratula)
            binding.titulo.text = it.title
            binding.TotalVotos.text = it.votes.toString()
            binding.votes.text = it.votes.toString()
            binding.duracion.text = ConvertSeg.convertSegToMin(it.duration)
            binding.seccion.text = it.rating

            buttonClicked(it.url)
        })
    }

    private fun buttonClicked(url: String) {
        binding.frameLayout.setOnClickListener {
            val intent = Intent(this, ReproductorActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }
    }

    private fun getDevice() {
        device = prefs.getDevice()
    }

    private fun getToken() {
        token = prefs.getToken()
    }

    private fun hide() {
        supportActionBar?.hide()
    }

    private fun getId() {
       id = intent.getStringExtra("id").toString()
    }
}