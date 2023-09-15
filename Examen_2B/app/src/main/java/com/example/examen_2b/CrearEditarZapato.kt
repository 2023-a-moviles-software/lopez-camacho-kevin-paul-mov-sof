package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen_2b.BZapato
import com.example.examen_2b.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearEditarZapato : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nuevo_zapato)

        val botonCrearNuevoZapato = findViewById<Button>(R.id.btn_nuevozapato)
        botonCrearNuevoZapato.setOnClickListener{
            cargarZapatos()
            irActividad(ListarZapatos::class.java)
        }

    }

    fun cargarZapatos(){
        val idMarca = findViewById<EditText>(R.id.et_new_idmarca).text.toString()
        val nombre = findViewById<EditText>(R.id.et_new_nombre).text.toString()
        val talla = findViewById<EditText>(R.id.et_new_talla).text.toString()
        val anoEstreno = findViewById<EditText>(R.id.et_new_anolanzamiento).text.toString()
        val zapatoVigente = findViewById<EditText>(R.id.et_new_zapatovigente).text.toString()

        crearZapatos(idMarca, nombre, talla, anoEstreno, zapatoVigente)
    }

    private fun crearZapatos(
        idMarca: String,
        nombre: String,
        talla:  String,
        anoEstreno: String,
        zapatoVigente: String
        ) {
            val nuevoZapato = BZapato(
                id = idMarca,
                nombre = nombre,
                talla = talla,
                anoEstreno = anoEstreno,
                zapatoVigente = zapatoVigente
            )

            val db = Firebase.firestore

            db.collection("zapatos")
                .document(nombre.toString())
                .set(nuevoZapato)
                .addOnSuccessListener {  }
                .addOnFailureListener {  }
        }

        fun irActividad(clase:Class<*>){
            val intent = Intent(this, clase)
            startActivity(intent)
        }
    }

