package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearNuevaMarca : AppCompatActivity() {
    val arreglo = BbaseMemoriaMarca.arregloBMarca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nueva_marca)

        val botonCrearNuevaMarca = findViewById<Button>(R.id.btn_newm_marca)
        botonCrearNuevaMarca.setOnClickListener{
            crearMarca()
            irActividad(MainActivity::class.java)
        }

    }

    fun crearMarca(){
        val idMarca = findViewById<EditText>(R.id.et_newm_idmarca).text.toString()
        val nombre = findViewById<EditText>(R.id.et_newm_nombre).text.toString()
        val promedioVentasMensual = findViewById<EditText>(R.id.et_newm_ventasmensuales).text.toString()
        val anoLanzamiento = findViewById<EditText>(R.id.et_newm_anolanzamiento).text.toString()
        val marcaVigente = findViewById<EditText>(R.id.et_newm_marcavigente).text.toString()
        //arreglo.add(
           // BMarca("${idMarca}","${nombre}", "${promedioVentasMensual}", "${anoLanzamiento}", "${marcaVigente}")
        //)
    }

    fun irActividad(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}