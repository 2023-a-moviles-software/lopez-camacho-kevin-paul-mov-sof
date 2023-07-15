package com.example.examen1b

class BZapato (
    var idMarca: String?,
    var nombre: String?,
    var talla:String?,
    var anoEstreno:String?,
    var zapatoVigente:String?,
){
    override fun toString(): String {
        return "${nombre}"
    }
}