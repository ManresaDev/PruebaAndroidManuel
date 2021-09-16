package com.manresa.pruebaandroidmanuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manresa.pruebaandroidmanuel.R
import com.manresa.pruebaandroidmanuel.data.model.Content
import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import com.manresa.pruebaandroidmanuel.databinding.ItemRowCarteleraBinding
import com.manresa.pruebaandroidmanuel.utils.ConvertSeg
import com.squareup.picasso.Picasso

class CarteleraAdapter(private val context: Context) : RecyclerView.Adapter<CarteleraAdapter.CarteleraViewHolder>() {
    private var dataList = mutableListOf<Content>()

    fun setListData(data : MutableList<Content>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarteleraViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_cartelera, parent, false)

        return CarteleraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarteleraViewHolder, position: Int) {
        val cartelera = dataList[position]
        holder.bindView(cartelera)

    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }


    inner class CarteleraViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val binding = ItemRowCarteleraBinding.bind(itemview)

        fun bindView(cartelera : Content){
            binding.titulo.text = cartelera.title
            binding.seccion.text = cartelera.section
            binding.duracion.text = ConvertSeg.convertSegToMin(cartelera.duration)
            Picasso.with(itemView.context).load(cartelera.cover).fit().into(binding.caratula)
        }
    }


}