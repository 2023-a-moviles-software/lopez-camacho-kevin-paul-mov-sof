package com.example.examen_2b

class BMarca(
    var id: String?,
    var nombre: String?,
    var promedioVentasMensual:String?,
    var anoLanzamiento:String?,
    var marcaVigente:String?
) {
    override fun toString(): String {
        return "${nombre}"
    }
}