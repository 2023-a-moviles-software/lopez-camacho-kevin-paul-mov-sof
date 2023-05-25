import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    println("Program arguments: ${args.joinToString()}")

    //INMUTABLES (NO se reasignan "=")
    val inmutable : String = "Adrian";

    //Mutables (Re asignar )
    var mutable: String = "Vicente";
    mutable = "Adrian";

    // val > var (mejor las imutable, solo en caso de variables reasignables usamos var

    //Duck Typing
    var ejemploVariable = " Kevin Lopez "
    val edadEjemplo : Int = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases java
    val fechaNacimiento: Date = Date()

    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        } else -> {
            println("No sabemos")
    }

    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"
    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)
    val sumaUno = suma(1,1)
    val sumaDos = suma(null,1)
    val sumaTres = suma(1,null)
    val sumaCuatro = suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(suma.pi)
    println(suma.elevarAlCuadrado(2))
    println(suma.historialSumas)
}
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
     constructor(
         uno: Int,
         dos: Int
     ){//Bloque de codigo del constructor
         this.numeroUno = uno
         this.numeroDos = dos
         println("Inicializando")
     }
}

abstract class Numeros(//Constructor PRIMARIO
// Ejemplo:
//uno : Int, (Parametro (sin modificar acceso))
//private var uno: Int, //Propiedad Publica Clase numeros.uno
//var uno: Int, //Propiedad de la clase 8por defecto es Publico)
//public var uno: Int,
protected val numeroUno: Int, // Propiedad de la clas protected numeros.numeroUno
protected val numeroDos: Int, // Propiedad de la clas protected numeros.numeroDos

//var cedula: String = "" (public es por defecto)
//private valorCalculado: Int = 0 (private)

){
    init{//bloque constructor secundario
        this.numeroUno; this.numeroDos; //This es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }}

class suma( //Constructor primario Suma
    unoParametro: Int,  //Parametro
    dosParametros: Int, //Parametro
    ):Numeros(unoParametro, dosParametros){ //extendiendo y mandando los parametyros (super)
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor( //Segundo constructor
        uno: Int?,//parametros
        dos:Int//Parametros
    ):this(
        if(uno ==null)0 else uno,
        dos
    )

    constructor( //Tercero constructor
        uno: Int,//parametros
        dos:Int?//Parametros
    ):this(
        uno,
        if(dos ==null)0 else dos,
    )
    constructor( //Cuarto constructor
        uno: Int?,//parametros
        dos:Int?//Parametros
    ):this(
        if(uno ==null)0 else uno,
        if(dos ==null)0 else dos,
    )

    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total) //this.aregarHistorial(total)
        return total
    }

    companion object {// Atributos y metodos "Compartidos" Singletons o Statics de esta clase
        // todas las instancias de esta clase comparten estos atribiutos y metodos
        //dentro del companion Object

        val pi = 3.14
        fun elevarAlCuadrado (num: Int): Int{
            return num*num
        }

        val historialSumas = ArrayList<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }

    }
}

//void -> unit
fun imprimirNombre (nombre: String): Unit{
    println("Nombre : ${nombre}") //templete strings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial : Double? = null, // Opcional null -> nullable
):Double{
    // Int -> Int? (nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo* (100/tasa) + bonoEspecial
    }
}