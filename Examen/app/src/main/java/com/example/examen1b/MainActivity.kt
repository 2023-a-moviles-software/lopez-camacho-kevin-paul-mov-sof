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

class MainActivity : AppCompatActivity() {
    val arreglo = BbaseMemoriaMarca.arregloBMarca
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCrearNuevaMarca = findViewById<Button>(R.id.btn_crear_marca)
        botonCrearNuevaMarca.setOnClickListener {
            irActividad(CrearNuevaMarca::class.java)
        }

        //Adaptador
        val listView = findViewById<ListView>(R.id.lv_marcas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                editar(idItemSeleccionado, EditarMarca::class.java)
                return true
            }

            R.id.mi_eliminar -> {
                eliminar(idItemSeleccionado)
                return true
            }
            R.id.mi_ver_zapatos -> {
                verZapatos(idItemSeleccionado, ListarZapatos::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
        super.onContextItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id de la lista
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id

    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun editar(idItemSeleccionado: Int, clase: Class<*>){
        //Enviar Parametro
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }

    fun eliminar(idItemSeleccionado: Int){
        arreglo.removeAt(idItemSeleccionado)
        val listView = findViewById<ListView>(R.id.lv_marcas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    private fun verZapatos(idItemSeleccionado: Int, clase: Class<*>) {
        //Enviar Parametro
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }

}