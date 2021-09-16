package com.manresa.pruebaandroidmanuel.data.model

data class User(var error : Boolean,
                var message: String,
                var authorized : Boolean,
                var token : String)