import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

data class Marca(
    val id_marca:Int,
    val nombre: String,
    val ventas_promedio:Double,
    val ano_lanzamiento:Int,
    val vigencia:String
)

data class Zapatos(
    val id_zapato:Int,
    val id_marca:Int,
    val nombre: String,
    val talla:Int,
    val ano_estreno:Int,
    val zapatos_vigentes:String
)

class Sqlite {
    //Coneccion a la base de datos
    private val url = "jdbc:sqlite:DB3Sqlite"
    private val connection: Connection = DriverManager.getConnection(url)

    private val statement: Statement = connection.createStatement()

    //Crear Tablas---------------------------------------------------------------------------------------------------

    fun crearTablas(){

        val crearTablaMarca = """
            CREATE TABLE IF NOT EXISTS MARCA (
            ID_MARCA            INTEGER       PRIMARY KEY NOT NULL,
            NOMBRE              CHAR(50), 
            VENTAS_PROMEDIO     REAL,
            ANO_LANZAMIENTO     INTEGER,
            VIGENCIA            CHAR(50)
        );
        """.trimIndent()
        try {
            statement.executeUpdate(crearTablaMarca)
        }catch(e: Exception){
            println("Tabla no creada")
        }


        val crearTablaZapatos = """
            CREATE TABLE IF NOT EXISTS ZAPATO (
            ID_ZAPATO           INTEGER       PRIMARY KEY NOT NULL,
            ID_MARCA1           INTEGER,
            NOMBRE              CHAR(100), 
            TALLA               INTEGER,
            ANO_ESTRENO         INTEGER,
            DISPONIBILIDAD      CHAR(100)
        );
        """.trimIndent()

        try {
            statement.executeUpdate(crearTablaZapatos)
        }catch(e: Exception){
            println("Tabla no creada Zapatos")
        }


    }

    //MARCA---------------------------------------------------------------------------------------------------
    fun crearMarca(id_marca:Int,
                   nombre: String,
                   ventas_promedio:Double,
                   ano_lanzamiento:Int,
                   vegencia:String
    ):Boolean{
        val insertMarca = """
            INSERT INTO MARCA (ID_MARCA, NOMBRE, VENTAS_PROMEDIO, ANO_LANZAMIENTO, VIGENCIA) 
            VALUES ('$id_marca', '$nombre', '$ventas_promedio', '$ano_lanzamiento', '$vegencia')
        """.trimIndent()
        try {
            statement.executeUpdate(insertMarca)
            return true
        }catch (e: Exception){
            println("Marcar no Ingresada")
            return false
        }
    }

    fun actualizarMarca(id_marca:Int,
                        nombre: String,
                        ventas_promedio:Double,
                        ano_lanzamiento:Int,
                        vegencia:String
    ):Boolean{
        val actualizar_marca= """
            UPDATE MARCA
            SET NOMBRE = ?, VENTAS_PROMEDIO = ?, ANO_LANZAMIENTO = ?, VIGENCIA = ?
            WHERE ID_MARCA = ?
        """.trimIndent()
        try {
            val statementActualizar = connection.prepareStatement(actualizar_marca)
            statementActualizar.setString(1, nombre)
            statementActualizar.setDouble(2, ventas_promedio)
            statementActualizar.setInt(3, ano_lanzamiento)
            statementActualizar.setString(4, vegencia)
            statementActualizar.setInt(5, id_marca)

            statementActualizar.executeUpdate()
            statementActualizar.close()
            return true

        }catch (e: Exception){
            println("No fue posible actualizar")
            return false
        }
    }

    fun eliminarMarca(id_marca: Int):Boolean{
        val eliminar_por_id = """
            DELETE FROM MARCA
            WHERE ID_MARCA  = '$id_marca'
        """.trimIndent()
        try {
            statement.executeUpdate(eliminar_por_id)
            return true
        }catch (e: Exception){
            println("La marca no pudo ser eliminada")
            return false
        }
    }
    fun consultar_por_id(id_marca: Int): MutableList<Marca>? {
        val consultar_por_id = """
            SELECT * FROM MARCA
            WHERE ID_MARCA = '$id_marca'
        """.trimIndent()

        val marca = mutableListOf<Marca>()

        val marcaConsulta = statement.executeQuery(consultar_por_id)
        try {
            while (marcaConsulta.next()){
                val id_marca = marcaConsulta.getInt("ID_MARCA")
                val nombre = marcaConsulta.getString("NOMBRE")
                val ventas_promedio = marcaConsulta.getDouble("VENTAS_PROMEDIO")
                val ano_lanzamiento = marcaConsulta.getInt("ANO_LANZAMIENTO")
                val vigencia = marcaConsulta.getString("VIGENCIA")

                marca.add(Marca(id_marca, nombre, ventas_promedio, ano_lanzamiento, vigencia))
            }

            return marca
        }catch (e: Exception){
            println("No se pudieron consultar Marcas")
            return null
        }
        marcaConsulta.close()
    }
    fun consultar_marcas(): MutableList<Marca>? {
        val consultar_marcas = """
            SELECT * FROM MARCA
        """.trimIndent()

        val marca = mutableListOf<Marca>()

        val marcaConsulta = statement.executeQuery(consultar_marcas)
        try {
            while (marcaConsulta.next()){
                val id_marca = marcaConsulta.getInt("ID_MARCA")
                val nombre = marcaConsulta.getString("NOMBRE")
                val ventas_promedio = marcaConsulta.getDouble("VENTAS_PROMEDIO")
                val ano_lanzamiento = marcaConsulta.getInt("ANO_LANZAMIENTO")
                val vigencia = marcaConsulta.getString("VIGENCIA")

                marca.add(Marca(id_marca, nombre, ventas_promedio, ano_lanzamiento, vigencia))
            }

            return marca
        }catch (e: Exception){
            println("No se pudieron consultar Marcas")
            return null
        }
        marcaConsulta.close()
    }

    //ZAPATO---------------------------------------------------------------------------------------------------
    fun crearZapato(id_zapato:Int,
                    id_marca1:Int,
                    nombre: String,
                    talla:Int,
                    ano_estreno:Int,
                    disponibilidad:String
    ):Boolean{
        val insertZapato = """
            INSERT INTO ZAPATO (ID_ZAPATO, ID_MARCA1, NOMBRE, TALLA, ANO_ESTRENO, DISPONIBILIDAD) 
            VALUES ('$id_zapato', '$id_marca1', '$nombre', '$talla', '$ano_estreno', '$disponibilidad')
        """.trimIndent()
        try {
            statement.executeUpdate(insertZapato)
            return true
        }catch (e: Exception){
            println("Zapato no Ingresada")
            return false
        }
    }

    fun actualizarZapato(nombre: String,
                         talla:Int,
                         ano_estreno:Int,
                         zapatos_vigentes:String,
                         id_zapato:Int
    ):Boolean{
        val actualizar_zapato= """
            UPDATE ZAPATO
            SET NOMBRE = ?, TALLA = ?, ANO_ESTRENO = ?, DISPONIBILIDAD = ?
            WHERE ID_ZAPATO = ?
        """.trimIndent()
        try {
            val statementActualizar = connection.prepareStatement(actualizar_zapato)
            statementActualizar.setString(1, nombre)
            statementActualizar.setInt(2, talla)
            statementActualizar.setInt(3, ano_estreno)
            statementActualizar.setString(4, zapatos_vigentes)
            statementActualizar.setInt(5, id_zapato)

            statementActualizar.executeUpdate()
            statementActualizar.close()
            return true

        }catch (e: Exception){
            println("No fue posible actualizar")
            return false
        }
    }

    fun eliminarZapato(id_zapato: Int):Boolean{
        val eliminar_por_id = """
            DELETE FROM ZAPATO
            WHERE ID_ZAPATO  = '$id_zapato'
        """.trimIndent()
        try {
            statement.executeUpdate(eliminar_por_id)
            return true
        }catch (e: Exception){
            println("El zapato no pudo ser eliminada")
            return false
        }
    }
    fun consultar_por_id_Zapato(id_zapato: Int): MutableList<Zapatos>? {
        val consultar_por_id = """
            SELECT * FROM ZAPATO
            WHERE ID_MARCA1 = '$id_zapato'
        """.trimIndent()

        val zapato = mutableListOf<Zapatos>()

        val zapatoConsulta = statement.executeQuery(consultar_por_id)
        try {
            while (zapatoConsulta.next()){
                val id_zapato = zapatoConsulta.getInt("ID_ZAPATO")
                val id_marca = zapatoConsulta.getInt("ID_MARCA1")
                val nombre = zapatoConsulta.getString("NOMBRE")
                val talla = zapatoConsulta.getInt("TALLA")
                val ano_estreno = zapatoConsulta.getInt("ANO_ESTRENO")
                val zapatos_vigentes = zapatoConsulta.getString("DISPONIBILIDAD")

                zapato.add(Zapatos(id_zapato, id_marca, nombre, talla, ano_estreno, zapatos_vigentes))
            }

            return zapato
        }catch (e: Exception){
            println("No se pudieron consultar Marcas")
            return null
        }
        zapatoConsulta.close()
    }
    fun consultar_Zapatos(): MutableList<Zapatos>? {
        val consultar_zapatos = """
            SELECT * FROM ZAPATO
        """.trimIndent()

        val zapato = mutableListOf<Zapatos>()

        val zapatoConsulta = statement.executeQuery(consultar_zapatos)
        try {
            while (zapatoConsulta.next()){
                val id_zapato = zapatoConsulta.getInt("ID_ZAPATO")
                val id_marca = zapatoConsulta.getInt("ID_MARCA1")
                val nombre = zapatoConsulta.getString("NOMBRE")
                val talla = zapatoConsulta.getInt("TALLA")
                val ano_estreno = zapatoConsulta.getInt("ANO_ESTRENO")
                val zapatos_vigentes = zapatoConsulta.getString("DISPONIBILIDAD")

                zapato.add(Zapatos(id_zapato, id_marca, nombre, talla, ano_estreno, zapatos_vigentes))
            }

            return zapato
        }catch (e: Exception){
            println("No se pudieron consultar Marcas")
            return null
        }
        zapatoConsulta.close()
    }

    //---------------------------------------------------------------------------------------------------------
    fun close(){
        statement.close()
        connection.close()
    }


}