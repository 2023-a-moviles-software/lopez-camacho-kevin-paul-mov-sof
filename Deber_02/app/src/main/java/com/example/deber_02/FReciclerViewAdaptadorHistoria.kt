package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FReciclerViewAdaptadorHistoria (
    private val contexto: MainActivity,
    private val lista: ArrayList<BHistoria>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FReciclerViewAdaptadorHistoria.MyViewHolder>(){
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val usuarioHistoriaTextView: TextView
        val imagenPerfilImageView: ImageView


        init {
            usuarioHistoriaTextView = view.findViewById(R.id.tv_usuarioHistoria)
            imagenPerfilImageView = view.findViewById(R.id.imv_imagenPerfilHistoria)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_historias,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val publicacionActual = this.lista[position]
        holder.usuarioHistoriaTextView.text = publicacionActual.usuario
        holder.imagenPerfilImageView.setImageResource(publicacionActual.imagenPerfil)
    }

    //Tamano del Arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}