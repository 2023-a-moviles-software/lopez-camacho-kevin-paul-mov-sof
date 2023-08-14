fun main(args: Array<String>) {
    val dataBaseSQLite = Sqlite()
    dataBaseSQLite.crearTablas()
    menuPrincipal()
}

fun menuPrincipal(): Unit{
    val dataBaseSQLite = Sqlite()
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
            println("Ingrese el ID de la Marca")
            val idMarca:Int = readln().toInt()
            val marcas = dataBaseSQLite.consultar_por_id(idMarca)
            for (marca in marcas!!){
                println("--------------Marca-------------------")
                println("ID Marca: " + marca.id_marca +
                        "\nNombre: " + marca.nombre +
                        "\nPromedio de ventas de zapatos mensual: " + marca.ventas_promedio +
                        "\nAño de lanazamiento: " + marca.ano_lanzamiento +
                        "\nLa marca esta vigente: " + marca.vigencia)
            }
            val zapatos = dataBaseSQLite.consultar_por_id_Zapato(idMarca)
            println("------------------------------------")
            for (zapato in zapatos!!){
                println("---------------------------------")
                println("ID Zapato: " + zapato.id_zapato +
                        "\nID Marca: " + zapato.id_marca +
                        "\nNombre: " + zapato.nombre +
                        "\nTalla: " + zapato.talla +
                        "\nAño de Estreno: " + zapato.ano_estreno +
                        "\nExisten pares disponibles: " + zapato.zapatos_vigentes)
            }
            menuPrincipal()}
        (4) -> {}
        else -> {println("Opcion NO VALIDA")
        }
    }
}

//Marcas----------------------------------------------------------------------------------------------------------------------------------------

data class Marca1(
    val id_marca:Int,
    val nombre: String,
    val ventas_promedio:Double,
    val ano_lanzamiento:Int,
    val vigencia:String
)

fun menuMarca(): Unit{
    val dataBaseSQLite = Sqlite()
    println("\t-----------------MENU MARCAS-----------------")
    println("1. Crear nueva Marca")
    println("2. Modificar Marca")
    println("3. Eliminar Marca")
    println("4. Mostrar Marcas")
    println("5. Volver")
    val menuMarcas = readln().toInt()
    when (menuMarcas){
        (1) -> {val marca = crearMarca()
                dataBaseSQLite.crearMarca(marca.id_marca, marca.nombre, marca.ventas_promedio, marca.ano_lanzamiento, marca.vigencia)
            println("Marca agregada exitosamente")
            menuMarca()
        }
        (2) -> {
            println("\t-------------------Modificar Marca-------------------")
            println("Ingrese el ID de la marca a Modificar")
            val idModificarMarca:Int = readln().toInt()
            val marca = modificarMarca(idModificarMarca)
            dataBaseSQLite.actualizarMarca(marca.id_marca, marca.nombre, marca.ventas_promedio, marca.ano_lanzamiento, marca.vigencia)
            println("Marca Modificada Exitosamente")
            menuMarca()
        }

        (3) -> {
            println("\t-------------------Eliminar Marca-------------------")
            println("Ingrese el ID de la marca a Eliminar")
            val idEliminarMarca:Int = readln().toInt()
            dataBaseSQLite.eliminarMarca(idEliminarMarca)
            println("Marca Eliminada exitosamente")
            menuMarca()
        }
        (4) -> {
            println("\t-------------------Mostrar Marcas-------------------")
            val marcas = dataBaseSQLite.consultar_marcas()
            for (marca in marcas!!){
                println("---------------------------------")
                println("ID Marca: " + marca.id_marca +
                        "\nNombre: " + marca.nombre +
                        "\nPromedio de ventas de zapatos mensual: " + marca.ventas_promedio +
                        "\nAño de lanazamiento: " + marca.ano_lanzamiento +
                        "\nLa marca esta vigente: " + marca.vigencia)
            }
            menuMarca()
        }
        (5) -> {menuPrincipal()}
        else -> { println("Opcion NO VALIDA") }
    }

}
fun crearMarca(): Marca1{
    println("Codigo único de la marca:")
    val id_marca:Int = readln().toInt()
    println("Nombre:")
    val nombre: String = readln().toString()
    println("Promedio de ventas de zapatos mensual:")
    val ventas_promedio:Double = readln().toDouble()
    println("Año de lanazamiento:")
    val ano_lanzamiento:Int = readln().toInt()
    println("La marca esta vigente [true/false]:")
    val vigencia:String = readln().toString()

    return Marca1 (id_marca, nombre, ventas_promedio, ano_lanzamiento, vigencia)
}
fun modificarMarca(idModificarMarca: Int): Marca1{
    println("Nombre:")
    val nombre: String = readln().toString()
    println("Promedio de ventas de zapatos mensual:")
    val ventas_promedio:Double = readln().toDouble()
    println("Año de lanazamiento:")
    val ano_lanzamiento:Int = readln().toInt()
    println("La marca esta vigente [true/false]:")
    val vigencia:String = readln().toString()

    return Marca1 (idModificarMarca, nombre, ventas_promedio, ano_lanzamiento, vigencia)
}

//Zapatos----------------------------------------------------------------------------------------------------------------------------------------
data class Zapatos1(
    val id_zapato:Int,
    val id_marca: Int,
    val nombre: String,
    val talla:Int,
    val ano_estreno:Int,
    val zapatos_vigentes:String
)

fun menuZapatos(): Unit{
    val dataBaseSQLite = Sqlite()
    println("\t-----------------MENU ZAPATOS-----------------")
    println("1. Crear nuevos Zapatos")
    println("2. Modificar Zapatos")
    println("3. Eliminar Zapatos")
    println("4. Mostrar Zapatos")
    println("5. Volver")
    val menuZapatos = readln().toInt()
    when (menuZapatos){
        (1) -> {val zapato = crearZapatos()
            dataBaseSQLite.crearZapato(zapato.id_zapato, zapato.id_marca, zapato.nombre, zapato.talla, zapato.ano_estreno, zapato.zapatos_vigentes)
            println("Zapato agregada exitosamente")
            menuZapatos()
        }
        (2) -> {
            println("\t-------------------Modificar Zapato-------------------")
            println("Ingrese el ID del zapato a Modificar")
            val idModificarZapato:Int = readln().toInt()
            val zapato = modificarZapato(idModificarZapato)
            dataBaseSQLite.actualizarZapato( zapato.nombre, zapato.talla, zapato.ano_estreno, zapato.zapatos_vigentes, zapato.id_zapato)
            println("Zapato Modificada Exitosamente")
            menuMarca()
        }

        (3) -> {
            println("\t-------------------Eliminar Zapatos-------------------")
            println("Ingrese el ID del zapato a Eliminar")
            val idEliminarZapato:Int = readln().toInt()
            dataBaseSQLite.eliminarZapato(idEliminarZapato)
            println("Zapato Eliminada exitosamente")
            menuZapatos()
        }
        (4) -> {
            println("\t-------------------Mostrar Zapatos-------------------")
            val zapatos = dataBaseSQLite.consultar_Zapatos()
            for (zapato in zapatos!!){
                println("---------------------------------")
                println("ID Zapato: " + zapato.id_zapato +
                        "\nID Marca: " + zapato.id_marca +
                        "\nNombre: " + zapato.nombre +
                        "\nTalla: " + zapato.talla +
                        "\nAño de Estreno: " + zapato.ano_estreno +
                        "\nExisten pares disponibles: " + zapato.zapatos_vigentes)
            }
            menuZapatos()
        }
        (5) -> {menuPrincipal()}
        else -> { println("Opcion NO VALIDA") }
    }
}
fun crearZapatos(): Zapatos1{
    println("Codigo único del zapato:")
    val id_zapato:Int = readln().toInt()
    println("Codigo único de la marca:")
    val id_marca:Int = readln().toInt()
    println("Nombre:")
    val nombre:String = readln().toString()
    println("Talla:")
    val talla:Int = readln().toInt()
    println("Año de Estreno:")
    val ano_estreno:Int = readln().toInt()
    println("Existen pares disponibles [true/false]:")
    val disponibilidad:String = readln().toString()

    return Zapatos1(id_zapato, id_marca, nombre, talla, ano_estreno, disponibilidad)
}
fun modificarZapato(id_zapato: Int): Zapatos1{
    println("---------------------------------------")
    println("Codigo único de la marca:")
    val id_marca:Int = readln().toInt()
    println("Nombre:")
    val nombre: String = readln().toString()
    println("Talla:")
    val talla:Int = readln().toInt()
    println("Año de Estreno:")
    val ano_estreno:Int = readln().toInt()
    println("Existen pares disponibles [true/false]:")
    val zapatos_vigente:String = readln().toString()

    return Zapatos1(id_zapato, id_marca, nombre, talla, ano_estreno, zapatos_vigente)
}

