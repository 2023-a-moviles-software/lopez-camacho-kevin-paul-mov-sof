import java.io.File
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption

fun main(args: Array<String>) {
    menuPrincipal()
}

fun menuPrincipal(): Unit{
    var zapatosfile = File("Resources/txt/Zapatos.txt")
    var marcasfile = File("Resources/txt/Marcas.txt")
    var zapatosDatos = mutableListOf<Zapatos>()
    var marcaDatos = mutableListOf<Marca>()
    marcaDatos = datosMarca(marcasfile)
    zapatosDatos = datosZapatos(zapatosfile)
    println("\t-----------------ZAPATOS X MARCAS-----------------")
    println("1. Marcas")
    println("2. Zapatos")
    println("3. Mostrar MARCAS Y ZAPATOS")
    println("4. Salir")
    val menuInicio = readln().toInt()
    when (menuInicio){
        (1) -> {menuMarca()}
        (2) -> {menuZapatos()}
        (3) -> {
            println("\t-------------------Marcas X Zapatos-------------------")
            marcaDatos = datosMarca(marcasfile)
            zapatosDatos = datosZapatos(zapatosfile)
            mostrarMarcasXZapatos(marcaDatos, zapatosDatos )
            menuPrincipal()}
        (4) -> {}
        else -> {println("Opcion NO VALIDA")
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

data class Marca(
    val codigoUnico:Int,
    val nombre: String,
    val promedioZapatos:Double,
    val anoLanzamiento:Int,
    val marcaVigente:Boolean
)

fun menuMarca(): Unit{
    var marcasfile = File("Resources/txt/Marcas.txt")
    var marcaDatos = mutableListOf<Marca>()
    marcaDatos = datosMarca(marcasfile)
    println("\t-----------------MENU MARCAS-----------------")
    println("1. Crear nueva Marca")
    println("2. Modificar Marca")
    println("3. Eliminar Marca")
    println("4. Mostrar Marcas")
    println("5. Volver")
    val menuMarcas = readln().toInt()
    when (menuMarcas){
        (1) -> {val marca = crearMarca()
                marcaDatos.add(marca)
            println("Marca agregada exitosamente")
            guardarMarcas(marcasfile, marcaDatos)
            menuMarca()
        }
        (2) -> {
            println("\t-------------------Modificar Marca-------------------")
            println("Ingrese el nombre de la marca a Modificar")
            val nombreEliminarMarca:String = readln().toString()
            eliminarMarca(marcaDatos, nombreEliminarMarca)
            guardarMarcas(marcasfile, marcaDatos)
            val marca = crearMarca()
            marcaDatos.add(marca)
            guardarMarcas(marcasfile, marcaDatos)
            println("Marca Modificada Exitosamente")
            menuMarca()
        }

        (3) -> {
            println("\t-------------------Eliminar Marca-------------------")
            println("Ingrese el nombre de la marca a eliminar")
            val nombreEliminarMarca:String = readln().toString()
            eliminarMarca(marcaDatos, nombreEliminarMarca)
            guardarMarcas(marcasfile, marcaDatos)
            println("Marca Eliminada exitosamente")
            menuMarca()
        }
        (4) -> {
            println("\t-------------------Mostrar Marcas-------------------")
            marcaDatos = datosMarca(marcasfile)
            mostrarMarcas(marcaDatos)
            menuMarca()
        }
        (5) -> {menuPrincipal()}
        else -> { println("Opcion NO VALIDA") }
    }
}

fun datosMarca(file: File):MutableList<Marca> {
    val datosMarcas = mutableListOf<Marca>();
    if(file.exists()){
        file.readLines().forEach(){
                line->
            val datos=line.split(",");
            val marca = Marca(datos[0].toInt(), datos[1].toString(),
                datos[2].toDouble(), datos[3].toInt(),datos[4].toBoolean())
            datosMarcas.add(marca);
        }
    }
    return datosMarcas;
}
fun crearMarca(): Marca{
    println("Codigo único de la marca:")
    val codigoUnico:Int = readln().toInt()
    println("Nombre:")
    val nombre: String = readln().toString()
    println("Promedio de ventas de zapatos mensual:")
    val promedioZapatos:Double = readln().toDouble()
    println("Año de lanazamiento:")
    val anoLanzamiento:Int = readln().toInt()
    println("La marca esta vigente [true/false]:")
    val marcaVigente:Boolean = readln().toBoolean()

    return Marca (codigoUnico, nombre, promedioZapatos, anoLanzamiento, marcaVigente)
}
fun eliminarMarca(marcaDatos: MutableList<Marca>, nombreEliminarMarca:String): Unit{
    val buscarModelo= marcaDatos.find { it.nombre== nombreEliminarMarca }
    val indexMarca = marcaDatos.indexOf(buscarModelo)
    marcaDatos.removeAt(indexMarca)
}
fun mostrarMarcas(marcaDatos: MutableList<Marca>){
    var index:Int = 0
    println("\t-------------Marcas Registradas-------------")
    marcaDatos.forEachIndexed{index, marca ->
        println("-------------------------------")
        println("   ID :    "+marca.codigoUnico)
        println("   Nombre : "+marca.nombre)
        println("   Año lanzamiento : "+marca.anoLanzamiento)
        println("   Promedio ventas: "+marca.promedioZapatos)
        println("   Vigencia : "+marca.marcaVigente)
    }
}
fun guardarMarcas(marcasfile : File, marcaDatos: MutableList<Marca>){
    marcasfile .printWriter().use { out ->
        marcaDatos.forEach { marca ->
            out.println("${marca.codigoUnico},${marca.nombre},${marca.promedioZapatos},${marca.anoLanzamiento},${marca.marcaVigente}")
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

data class Zapatos(
    val codigoUnico:Int,
    val nombre: String,
    val talla:Double,
    val anoEstreno:Int,
    val zapatosVigente:Boolean
)

fun menuZapatos(): Unit{
    var zapatosfile = File("Resources/txt/Zapatos.txt")
    var marcasfile = File("Resources/txt/Marcas.txt")
    var zapatosDatos = mutableListOf<Zapatos>()
    var marcaDatos = mutableListOf<Marca>()
    marcaDatos = datosMarca(marcasfile)
    zapatosDatos = datosZapatos(zapatosfile)
    println("\t-----------------MENU ZAPATOS-----------------")
    println("1. Crear nuevos Zapatos")
    println("2. Modificar Zapatos")
    println("3. Eliminar Zapatos")
    println("4. Mostrar Zapatos")
    println("5. Volver")
    val menuZapatos = readln().toInt()
    when (menuZapatos){
        (1) -> {val zapato = crearZapatos()
            zapatosDatos.add(zapato)
            println("Zapatos agregada exitosamente")
            guardarZapatos(zapatosfile, zapatosDatos)
            menuZapatos()
        }
        (2) -> {
            println("\t-------------------Modificar Zapato-------------------")
            println("Ingrese el nombre del zapato a Modificar")
            val nombreEliminarZapatos:String = readln().toString()
            eliminarZapatos(zapatosDatos, nombreEliminarZapatos)
            guardarZapatos(zapatosfile, zapatosDatos)
            val zapato = crearZapatos()
            zapatosDatos.add(zapato)
            guardarZapatos(zapatosfile, zapatosDatos)
            println("Zapato Modificada Exitosamente")
            menuZapatos()
        }

        (3) -> {
            println("\t-------------------Eliminar Zapatos-------------------")
            println("Ingrese el nombre del zapato a eliminar")
            val nombreEliminarZapatos:String = readln().toString()
            eliminarZapatos(zapatosDatos, nombreEliminarZapatos)
            guardarZapatos(zapatosfile, zapatosDatos)
            println("Zapato Eliminada exitosamente")
            menuZapatos()
        }
        (4) -> {
            println("\t-------------------Mostrar Zapatos-------------------")
            marcaDatos = datosMarca(marcasfile)
            zapatosDatos = datosZapatos(zapatosfile)
            mostrarZapatos(marcaDatos, zapatosDatos )
            menuZapatos()
        }
        (5) -> {menuPrincipal()}
        else -> { println("Opcion NO VALIDA") }
    }
}

fun crearZapatos(): Zapatos{
    println("Codigo único de la marca:")
    val codigoUnico:Int = readln().toInt()
    println("Nombre:")
    val nombre: String = readln().toString()
    println("Talla:")
    val talla:Double = readln().toDouble()
    println("Año de Estreno:")
    val anoEstreno:Int = readln().toInt()
    println("Existen pares disponibles [true/false]:")
    val zapatosVigente:Boolean = readln().toBoolean()

    return Zapatos (codigoUnico, nombre, talla, anoEstreno, zapatosVigente)
}

fun eliminarZapatos(zapatosDatos: MutableList<Zapatos>, nombreEliminarZapatos: String): Unit{
    val buscarZapatos= zapatosDatos.find { it.nombre== nombreEliminarZapatos}
    val indexZapatos = zapatosDatos.indexOf(buscarZapatos)
    zapatosDatos.removeAt(indexZapatos)
}

fun mostrarZapatos(marcaDatos: MutableList<Marca>, zapatosDatos: MutableList<Zapatos>){
    var index:Int = 0
    println("\t-------------Zapatos -------------")
    marcaDatos.forEachIndexed{index, marca ->
        val i = marca.codigoUnico;
        println("--------------------------------")
        println("   Marca : "+marca.nombre)
        println("--------------------------------")
        zapatosDatos.forEachIndexed { index, zapato ->
            val j = zapato.codigoUnico;
            if(i==j){
                println("       ID :            "+zapato.codigoUnico)
                println("       Nombre:         "+zapato.nombre)
                println("       Talla :         "+zapato.talla)
                println("       Año Estreno :   "+zapato.anoEstreno)
                println("       Disponibilidad: "+zapato.zapatosVigente)
                println("--------------------------------")
            }
        }
    }
}

fun datosZapatos(file: File):MutableList<Zapatos> {
    val datosZapato = mutableListOf<Zapatos>();
    if(file.exists()){
        file.readLines().forEach(){
                line->
            val datos=line.split(",");
            val zapato = Zapatos(datos[0].toInt(), datos[1].toString(),
                datos[2].toDouble(), datos[3].toInt(),datos[4].toBoolean())
            datosZapato.add(zapato);
        }
    }
    return datosZapato;
}

fun guardarZapatos(zapatosfile: File, zapatosDatos: MutableList<Zapatos>){
    zapatosfile .printWriter().use { out ->
        zapatosDatos.forEach { zapato ->
            out.println("${zapato.codigoUnico},${zapato.nombre},${zapato.talla},${zapato.anoEstreno},${zapato.zapatosVigente}")
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun mostrarMarcasXZapatos(marcaDatos: MutableList<Marca>, zapatosDatos: MutableList<Zapatos>){
    var index:Int = 0
    println("-------------Marca -------------")
    marcaDatos.forEachIndexed{index, marca ->
        val i = marca.codigoUnico;
        println("   ID :    "+marca.codigoUnico)
        println("   Nombre : "+marca.nombre)
        println("   Año lanzamiento : "+marca.anoLanzamiento)
        println("   Promedio ventas: "+marca.promedioZapatos)
        println("   Vigencia : "+marca.marcaVigente)
        println("--------------------------------")
        zapatosDatos.forEachIndexed { index, zapato ->
            val j = zapato.codigoUnico;
            if(i==j){
                println("       ID :            "+zapato.codigoUnico)
                println("       Nombre:         "+zapato.nombre)
                println("       Talla :         "+zapato.talla)
                println("       Año Estreno :   "+zapato.anoEstreno)
                println("       Disponibilidad: "+marca.marcaVigente)
                println("--------------------------------")
            }
        }
    }
}