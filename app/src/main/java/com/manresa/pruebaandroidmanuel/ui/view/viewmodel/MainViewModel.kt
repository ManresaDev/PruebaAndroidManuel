package com.manresa.pruebaandroidmanuel.ui.view.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manresa.pruebaandroidmanuel.data.model.User
import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import com.manresa.pruebaandroidmanuel.domain.GetCarteleraUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    fun getCartelera(token : String, device : String) : LiveData<UserLoad>{
        var getCarteleraUseCase = GetCarteleraUseCase(token, device)
        val mutableData = MutableLiveData<UserLoad>()
        viewModelScope.launch{
            val result = getCarteleraUseCase()
            mutableData.value = result
        }
        return mutableData
    }
}

