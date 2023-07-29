package com.example.deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val arregloPublicaciones = BBaseDatosMemoria.arregloPublicaciones
    val arregloHistorias = BBaseDatosMemoria.arregloHistorias
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarRecyclerViewHistorias()
        inicializarRecyclerViewPublicaciones()
    }

    fun inicializarRecyclerViewPublicaciones(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_publicacion
        )

        val adaptador = FReciclerViewAdaptadorPublicacion(
            this,
            arregloPublicaciones,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(
                this,
            )
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewHistorias(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_historias
        )

        val adaptador = FReciclerViewAdaptadorHistoria(
            this,
            arregloHistorias,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        adaptador.notifyDataSetChanged()
    }
}