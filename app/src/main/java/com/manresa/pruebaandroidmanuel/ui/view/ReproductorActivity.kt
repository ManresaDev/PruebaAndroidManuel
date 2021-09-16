package com.manresa.pruebaandroidmanuel.ui.view

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.manresa.pruebaandroidmanuel.databinding.ActivityReproductorBinding

class ReproductorActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReproductorBinding
    private lateinit var url : String

    private lateinit var videoUri : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReproductorBinding.inflate(layoutInflater)

        setContentView(binding.root)

        fullScreen()

        getUrl()
        reproducir(url)
        hide()
    }

    private fun fullScreen() {
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private fun reproducir(trailer: String?) {

        videoUri = Uri.parse(trailer)

        val mediaController = android.widget.MediaController(this@ReproductorActivity)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(videoUri)
        binding.videoView.requestFocus()


        binding.videoView.setOnInfoListener { mp: MediaPlayer, what, extra ->

            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {

                binding.loadingVideo.visibility = View.VISIBLE

            } else if(what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                binding.loadingVideo.visibility = View.INVISIBLE
            }
            false
        }



        binding.videoView.start()

    }

    private fun getUrl() {
        url = intent.getStringExtra("url").toString()
    }

    private fun hide() {
        supportActionBar?.hide()
    }
}