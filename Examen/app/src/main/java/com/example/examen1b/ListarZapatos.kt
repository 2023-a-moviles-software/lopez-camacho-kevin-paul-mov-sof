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

class ListarZapatos : AppCompatActivity() {
    val arregloZapatos = BbaseMemoriaZapato.arregloBZapato
    val arregloZapatosID = BbaseMemoriaZapatosID.arregloBZapatos
    val arregloMarcas = BbaseMemoriaMarca.arregloBMarca

    var idItemSeleccionadoZapato = 0
    var idItemSeleccionadoMarca = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_zapatos)

        //id de la marca
        idItemSeleccionadoMarca = intent.getIntExtra("idItemSeleccionado", -1)

        val botonCrearNuevoZapato = findViewById<Button>(R.id.btn_crear_zapato)
        botonCrearNuevoZapato.setOnClickListener {
            irActividad(CrearNuevoZapato::class.java)
        }

        //Adaptador
        val listView = findViewById<ListView>(R.id.lv_zapatos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloZapatos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)
    }
    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                editar(idItemSeleccionadoZapato, EditarZapato::class.java)
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
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }

    fun eliminar(idItemSeleccionado: Int){
        arregloZapatos.removeAt(idItemSeleccionado)
        val listView = findViewById<ListView>(R.id.lv_zapatos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloZapatos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}