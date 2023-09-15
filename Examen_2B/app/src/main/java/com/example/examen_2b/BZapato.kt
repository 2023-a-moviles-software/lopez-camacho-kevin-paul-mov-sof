package com.example.examen_2b

class BZapato (
    var id: String?,
    var nombre: String?,
    var talla:String?,
    var anoEstreno:String?,
    var zapatoVigente:String?,
){
    override fun toString(): String {
        return "${nombre}"
    }
}