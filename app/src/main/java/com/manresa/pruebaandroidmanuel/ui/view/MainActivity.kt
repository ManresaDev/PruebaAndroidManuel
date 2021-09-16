package com.manresa.pruebaandroidmanuel.ui.view

import android.content.Context
import android.content.Intent
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
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


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var token : String
    private lateinit var device : String
    private val viewmodel : MainViewModel by viewModels()
    private lateinit var carteleras : ArrayList<Content>

    companion object{
        lateinit var adapter : CarteleraAdapter
        lateinit var recyclerViewCartelera : RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buscador.setOnQueryTextListener(this)

        carteleras = ArrayList()


        recyclerViewCartelera = binding.recyclerview

        adapter = CarteleraAdapter(this)
        recyclerViewCartelera.layoutManager = GridLayoutManager(this,3)
        recyclerViewCartelera.adapter = adapter

        signOutClicked()
        getToken()
        getDevice()
        getCartelera()
        setRecycler()
        deslizamiento()

        hide()
    }

    private fun deslizamiento() {
        recyclerViewCartelera.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy >0 && binding.buscador.visibility == View.VISIBLE){
                    binding.buscador.visibility = View.GONE
                }else if(dy<0 && binding.buscador.visibility != View.VISIBLE){
                    binding.buscador.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setRecycler() {
        adapter.setListData(carteleras)
    }

    private fun getDevice() {
        device = prefs.getDevice()
    }

    private fun getCartelera() {
        binding.loading.visibility = View.VISIBLE
      viewmodel.getCartelera(token, device).observe(this, Observer {
          binding.titulo.text = it.user.name
          Picasso.with(this).load(it.user.avatar).into(binding.avatar);
        binding.loading.visibility = View.GONE
          //adapter.setListData(it.contents as MutableList<Content>)
          carteleras.addAll(it.contents)
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

    private fun searchByName(query : String){
        viewmodel.getCartelera(token, device).observe(this, Observer {
            carteleras.clear()
            for (result in it.contents){
                if(result.title.lowercase().contains(query)){
                    carteleras.add(result)
                }
            }
            adapter.notifyDataSetChanged()
            hideKeyBoard()
        })
    }
    private fun hideKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewroot.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}