package com.example.examen_2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearEditarMarca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nueva_marca)

        val botonCrearNuevaMarca = findViewById<Button>(R.id.btn_newm_marca)
        botonCrearNuevaMarca.setOnClickListener{
            recibirMarca()
            irActividad(MainActivity::class.java)
        }

    }

    fun recibirMarca(){
        val nombre = findViewById<EditText>(R.id.et_newm_nombre).text.toString()
        val promedioVentasMensual = findViewById<EditText>(R.id.et_newm_ventasmensuales).text.toString()
        val anoLanzamiento = findViewById<EditText>(R.id.et_newm_anolanzamiento).text.toString()
        val marcaVigente = findViewById<EditText>(R.id.et_newm_marcavigente).text.toString()
        val id = findViewById<EditText>(R.id.et_newm_idmarca).text.toString()

        crearMarca(id, nombre, promedioVentasMensual, anoLanzamiento, marcaVigente)

    }
    private fun crearMarca(
        id: String,
        nombre: String,
        promedioVentasMensual: String,
        anoLanzamiento: String,
        marcaVigente: String
    ) {
        val nuevaMarca = BMarca(
            id = id,
            nombre = nombre,
            promedioVentasMensual = promedioVentasMensual,
            anoLanzamiento = anoLanzamiento,
            marcaVigente= marcaVigente
        )

        val db = Firebase.firestore

        db.collection("marcas")
            .document(id.toString())
            .set(nuevaMarca)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    fun irActividad(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}