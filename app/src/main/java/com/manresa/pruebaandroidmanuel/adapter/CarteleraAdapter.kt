package com.manresa.pruebaandroidmanuel.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.manresa.pruebaandroidmanuel.R
import com.manresa.pruebaandroidmanuel.data.model.Content
import com.manresa.pruebaandroidmanuel.data.model.UserLoad
import com.manresa.pruebaandroidmanuel.databinding.ItemRowCarteleraBinding
import com.manresa.pruebaandroidmanuel.ui.view.PeliculaDetailActivity
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


            itemView.setOnClickListener{
                launchPeliculasDetails(cartelera.id)
            }
            var like = false
            binding.favorite.setOnClickListener {
               like = likeAnimation(binding.favorite, R.raw.favorite, like)
            }
        }
    }

    private fun likeAnimation(imageView: LottieAnimationView, animation : Int, like : Boolean) : Boolean{
        if(!like){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        }else{
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object :AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        imageView.setImageResource(R.drawable.favorite)
                        imageView.alpha = 1f
                    }
                })

        }
        return !like
    }

    private fun launchPeliculasDetails(id: String) {
        val intent = Intent(context, PeliculaDetailActivity::class.java)
        intent.putExtra("id", id)
        context.startActivity(intent)
    }


}