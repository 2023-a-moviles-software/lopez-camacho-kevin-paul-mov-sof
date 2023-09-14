package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearNuevoZapato : AppCompatActivity() {
    val arregloZapatos = BbaseMemoriaZapato.arregloBZapato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nuevo_zapato)

        val botonCrearNuevoZapato = findViewById<Button>(R.id.btn_nuevozapato)
        botonCrearNuevoZapato.setOnClickListener{
            crearZapato()
            irActividad(ListarZapatos::class.java)
        }

    }

    fun crearZapato(){
        val idMarca = findViewById<EditText>(R.id.et_new_idmarca).text.toString()
        val nombre = findViewById<EditText>(R.id.et_new_nombre).text.toString()
        val talla = findViewById<EditText>(R.id.et_new_talla).text.toString()
        val anoEstreno = findViewById<EditText>(R.id.et_new_anolanzamiento).text.toString()
        val zapatoVigente = findViewById<EditText>(R.id.et_new_zapatovigente).text.toString()
        //arregloZapatos.add(
           // BZapato("${idMarca}", "${nombre}",  "${talla}",  "${anoEstreno}",  "${zapatoVigente}")
        //)
    }

    fun irActividad(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}