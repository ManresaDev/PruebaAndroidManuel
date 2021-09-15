package com.manresa.pruebaandroidmanuel.utils

import java.math.BigInteger
import java.security.MessageDigest

class Desencrypt {
    companion object{
        fun desencryptMD5(password: String) : String{
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}