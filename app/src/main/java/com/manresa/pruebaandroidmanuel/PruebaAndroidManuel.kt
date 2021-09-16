package com.manresa.pruebaandroidmanuel

import android.app.Application
import com.manresa.pruebaandroidmanuel.utils.Prefs

class PruebaAndroidManuel : Application() {
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}