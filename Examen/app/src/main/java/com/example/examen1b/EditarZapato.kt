package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarZapato : AppCompatActivity() {
    val arregloZapatosID = BbaseMemoriaZapatosID.arregloBZapatos
    val arregloZapatos = BbaseMemoriaZapato.arregloBZapato
    var IDitemSeleccionadoZapato = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_zapato)

        IDitemSeleccionadoZapato = intent.getIntExtra("idItemSeleccionado", -1)
        //Traemos el item a modificar
        val IDmodificar = arregloZapatos[IDitemSeleccionadoZapato]

        //Asignar valores a modoficar a la interfaz
        val idMarca = findViewById<EditText>(R.id.et_edit_idmarca)
        //idMarca.setText(IDmodificar.idMarca)
        val nombre = findViewById<EditText>(R.id.et_edit_nombre)
        nombre.setText(IDmodificar.nombre)
        val talla = findViewById<EditText>(R.id.et_edit_talla)
        talla.setText(IDmodificar.talla)
        val anoEstreno = findViewById<EditText>(R.id.et_edit_anolanzamiento)
        anoEstreno.setText(IDmodificar.anoEstreno)
        val zapatoVigente = findViewById<EditText>(R.id.et_edit_zapatovigente)
        zapatoVigente.setText(IDmodificar.zapatoVigente)

        val editarZapato = findViewById<Button>(R.id.btn_edit_zapato)
        editarZapato.setOnClickListener{
            editar()
            irActividad(ListarZapatos::class.java)
        }
    }

    fun editar(){
        var idMarca = findViewById<EditText>(R.id.et_edit_idmarca).text.toString()
        var nombre = findViewById<EditText>(R.id.et_edit_nombre).text.toString()
        var talla = findViewById<EditText>(R.id.et_edit_talla).text.toString()
        var anoEstreno = findViewById<EditText>(R.id.et_edit_anolanzamiento).text.toString()
        var zapatoVigente = findViewById<EditText>(R.id.et_edit_zapatovigente).text.toString()

              //  arregloZapatos[IDitemSeleccionadoZapato ].idMarca = idMarca
                arregloZapatos[IDitemSeleccionadoZapato ].nombre = nombre
                arregloZapatos[IDitemSeleccionadoZapato ].talla = talla
                arregloZapatos[IDitemSeleccionadoZapato ].anoEstreno = anoEstreno
                arregloZapatos[IDitemSeleccionadoZapato ].zapatoVigente = zapatoVigente


    }

    fun irActividad(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}