package com.manresa.pruebaandroidmanuel.utils

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "MyDTB"
    val SHARED_TOKEN = "token"
    val SHARED_DEVICE = "device"

    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun saveToken(token : String){
        storage.edit().putString(SHARED_TOKEN, token).apply()
    }

    fun saveDevice(device : String){
        storage.edit().putString(SHARED_DEVICE, device).apply()
    }

    fun getDevice() : String{
        return storage.getString(SHARED_DEVICE, "")!!
    }

    fun getToken() : String{
        return storage.getString(SHARED_TOKEN, "")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}