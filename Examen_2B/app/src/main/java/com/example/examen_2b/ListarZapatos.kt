package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.examen_2b.BZapato
import com.example.examen_2b.R
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListarZapatos : AppCompatActivity() {

    var idItemSeleccionadoZapato = 0
    var idItemSeleccionadoMarca = 0

    var query: Query? = null
    val arreglo: ArrayList<BZapato> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_zapatos)

        //id de la marca
        idItemSeleccionadoMarca = intent.getIntExtra("idItemSeleccionado", -1)

        val botonCrearNuevoZapato = findViewById<Button>(R.id.btn_crear_zapato)
        botonCrearNuevoZapato.setOnClickListener {
            irActividad(CrearEditarZapato::class.java)
        }

        //Adaptador
        val listView = findViewById<ListView>(R.id.lv_zapatos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        cargarZapatos(adaptador)
        registerForContextMenu(listView)
    }

    private fun cargarZapatos(adaptador: ArrayAdapter<BZapato>) {
        val db = Firebase.firestore
        val marcasRefUnico = db.collection("zapatos")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        marcasRefUnico
            .whereEqualTo("id", "1" )
            .get()
            .addOnSuccessListener { // it => eso (lo que llegue)
                for (zapato in it){
                    zapato.id
                    anadirAArregloZapato(zapato)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Errores
            }
    }

    fun limpiarArreglo() {arreglo.clear()}

    fun anadirAArregloZapato(
        zapato: QueryDocumentSnapshot
    ){
        val nuevosZapatos = BZapato(
            zapato.data.get("id") as String?,
            zapato.data.get("nombre") as String?,
            zapato.data.get("talla") as String?,
            zapato.data.get("anoEstreno") as String?,
            zapato.data.get("zapatoVigente") as String?
        )

        arreglo.add(nuevosZapatos)
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                editar(idItemSeleccionadoZapato, CrearEditarZapato::class.java)
                return true
            }

            R.id.mi_eliminar -> {
                eliminar(idItemSeleccionadoZapato)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_zapatos, menu)
        //Obtener el id de la lista
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionadoZapato = id

    }

    fun editar(idItemSeleccionado: Int, clase: Class<*>){
        //Enviar Parametro
        eliminar(idItemSeleccionado)
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }

    fun eliminar(idItemSeleccionado: Int){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("zapatos")
        referenciaEjemploEstudiante
            .document(arreglo[idItemSeleccionado].nombre.toString()) // id usado para crear en la funcion "crearEjemplo"
            .delete() // elimina
            .addOnCompleteListener {  }
            .addOnSuccessListener {  }
        val listView = findViewById<ListView>(R.id.lv_zapatos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        cargarZapatos(adaptador)
    }

}