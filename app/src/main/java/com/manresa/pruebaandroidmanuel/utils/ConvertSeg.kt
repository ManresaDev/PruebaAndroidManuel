package com.manresa.pruebaandroidmanuel.utils

class ConvertSeg {
    companion object{
        fun convertSegToMin(seg : Int) : String{
            val horas = seg / 3600
            val minutos = ((seg - horas * 3600)/60)
            val segundos = seg - (horas*3600+minutos*60)

            return if(minutos < 10 && segundos < 10 ){
                "$horas:0$minutos:0$segundos"
            }else if(minutos < 10){
                "$horas:0$minutos:$segundos"
            }else if (segundos < 10){
                "$horas:$minutos:0$segundos"
            }else{
                "$horas:$minutos:$segundos"
            }
        }
    }
}