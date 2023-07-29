package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class FReciclerViewAdaptadorPublicacion (
    private val contexto: MainActivity,
    private val lista: ArrayList<BPublicacion>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FReciclerViewAdaptadorPublicacion.MyViewHolder>(){
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val usuarioTextView: TextView
        val likesTextView: TextView
        val descripcionTextView: TextView
        val fechaioTextView: TextView
        val imagenPerfilImageView: ImageView
        val imagenPublicacionImageView: ImageView

        init {
            usuarioTextView = view.findViewById(R.id.tv_usuario)
            likesTextView = view.findViewById(R.id.tv_likes)
            descripcionTextView = view.findViewById(R.id.tv_descripcion)
            fechaioTextView = view.findViewById(R.id.tv_fecha)
            imagenPerfilImageView = view.findViewById(R.id.imv_perfil)
            imagenPublicacionImageView = view.findViewById(R.id.imv_publicacion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_publicaciones,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val publicacionActual = this.lista[position]
        holder.usuarioTextView.text = publicacionActual.usuario
        holder.likesTextView.text = publicacionActual.likes
        holder.descripcionTextView.text = "${publicacionActual.usuario} ${publicacionActual.descripcion}"
        holder.fechaioTextView.text = publicacionActual.fecha
        holder.imagenPerfilImageView.setImageResource(publicacionActual.imagenPerfil)
        holder.imagenPublicacionImageView.setImageResource(publicacionActual.imagenPublicacion)
    }

    //Tamano del Arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}