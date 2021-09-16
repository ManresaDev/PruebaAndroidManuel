package com.manresa.pruebaandroidmanuel.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manresa.pruebaandroidmanuel.data.model.Pelicula
import com.manresa.pruebaandroidmanuel.domain.GetPeliculaUseCase
import kotlinx.coroutines.launch

class PeliculaViewModel : ViewModel() {
    fun getPelicula(token : String, device : String, id : String) : LiveData<Pelicula>{
        var getPeliculaUseCase = GetPeliculaUseCase(token, device, id)
        val mutableData = MutableLiveData<Pelicula>()

        viewModelScope.launch {
            val result = getPeliculaUseCase()
            mutableData.value = result
        }
        return mutableData
    }
}