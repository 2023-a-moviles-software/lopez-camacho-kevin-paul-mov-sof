package com.example.examen_2b

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
import com.example.examen1b.ListarZapatos
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<BMarca> = arrayListOf()
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonCrearNuevaMarca = findViewById<Button>(R.id.btn_crear_marca)
        botonCrearNuevaMarca.setOnClickListener {
            val intent = Intent(this, CrearEditarMarca::class.java)
            startActivity(intent)
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

        cargarMarcas(adaptador)
        registerForContextMenu(listView)
    }

    private fun cargarMarcas(adaptador: ArrayAdapter<BMarca>) {
        val db = Firebase.firestore
        val marcasRefUnico = db.collection("marcas")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        marcasRefUnico
            .get()
            .addOnSuccessListener { // it => eso (lo que llegue)
                for (marca in it){
                    marca.id
                    anadirAArregloMarca(marca)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Errores
            }
    }

    fun limpiarArreglo() {arreglo.clear()}

    fun anadirAArregloMarca(
        marca: QueryDocumentSnapshot
    ){
        val nuevaMarca = BMarca(
            marca.data.get("id") as String?,
            marca.data.get("nombre") as String?,
            marca.data.get("promedioVentasMensual") as String?,
            marca.data.get("anoLanzamiento") as String?,
            marca.data.get("marcaVigente") as String?
        )

        arreglo.add(nuevaMarca)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                editar(idItemSeleccionado, CrearEditarMarca::class.java)
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

    fun editar(idItemSeleccionado: Int, clase: Class<*>){
        //Enviar Parametro
        eliminar(idItemSeleccionado)
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }

    fun eliminar(idItemSeleccionado: Int){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("marcas")
        referenciaEjemploEstudiante
            .document(arreglo[idItemSeleccionado].id.toString()) // id usado para crear en la funcion "crearEjemplo"
            .delete() // elimina
            .addOnCompleteListener {  }
            .addOnSuccessListener {  }
        val listView = findViewById<ListView>(R.id.lv_marcas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        cargarMarcas(adaptador)
    }

    private fun verZapatos(idItemSeleccionado: Int, clase: Class<*>) {
        //Enviar Parametro
        val intentExplicitoEditar = Intent(this, clase)
        intentExplicitoEditar.putExtra("idItemSeleccionado", idItemSeleccionado)
        startActivity(intentExplicitoEditar)
    }



}