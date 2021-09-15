package com.manresa.pruebaandroidmanuel.utils

import android.util.Patterns
import java.util.regex.Pattern


class CheckLoginData {
    companion object{
        fun isDataValid(email: String, password: String) : Int{
            if(email.isEmpty() || password.isEmpty())
                return 0
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return 1
            else
                return -1
        }
    }
}