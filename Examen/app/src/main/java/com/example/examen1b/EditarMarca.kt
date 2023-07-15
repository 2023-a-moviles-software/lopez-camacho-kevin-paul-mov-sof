package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarMarca : AppCompatActivity() {
    val arreglo = BbaseMemoriaMarca.arregloBMarca
    //Indice del item seleccionado
    var IDitemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        IDitemSeleccionado = intent.getIntExtra("idItemSeleccionado", -1)
        //Traemos el item a modificar
        val IDmodificar = arreglo[IDitemSeleccionado]

        //Asignar valores a modoficar a la interfaz
        val idMarca = findViewById<EditText>(R.id.et_editm_idmarca)
        idMarca.setText(IDmodificar.idMarca)
        val nombre = findViewById<EditText>(R.id.et_editm_nombre)
        nombre.setText(IDmodificar.nombre)
        val promedioVentasMensual = findViewById<EditText>(R.id.et_editm_ventasmensuales)
        promedioVentasMensual.setText(IDmodificar.promedioVentasMensual)
        val anoLanzamiento = findViewById<EditText>(R.id.et_editm_anolanzamiento)
        anoLanzamiento.setText(IDmodificar.anoLanzamiento)
        val marcaVigente = findViewById<EditText>(R.id.et_editm_marcavigente)
        marcaVigente.setText(IDmodificar.marcaVigente)

        val botonCrearNuevaMarca = findViewById<Button>(R.id.btn_editm_editar)
        botonCrearNuevaMarca.setOnClickListener{
            editar()
            irActividad(MainActivity::class.java)
        }
    }

    fun editar(){
        var idMarca = findViewById<EditText>(R.id.et_editm_idmarca).text.toString()
        var nombre = findViewById<EditText>(R.id.et_editm_nombre).text.toString()
        var promedioVentasMensual = findViewById<EditText>(R.id.et_editm_ventasmensuales).text.toString()
        var anoLanzamiento = findViewById<EditText>(R.id.et_editm_anolanzamiento).text.toString()
        var marcaVigente = findViewById<EditText>(R.id.et_editm_marcavigente).text.toString()
        arreglo[IDitemSeleccionado].idMarca = idMarca
        arreglo[IDitemSeleccionado].nombre = nombre
        arreglo[IDitemSeleccionado].promedioVentasMensual = promedioVentasMensual
        arreglo[IDitemSeleccionado].anoLanzamiento = anoLanzamiento
        arreglo[IDitemSeleccionado].marcaVigente = marcaVigente
    }

    fun irActividad(clase:Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}

