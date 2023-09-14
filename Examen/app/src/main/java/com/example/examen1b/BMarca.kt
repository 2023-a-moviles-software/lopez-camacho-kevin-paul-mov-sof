package com.example.examen1b

class BMarca(
    var nombre: String?,
    var promedioVentasMensual:String?,
    var anoLanzamiento:String?,
    var marcaVigente:String?
) {
    override fun toString(): String {
        return "${nombre}"
    }
}