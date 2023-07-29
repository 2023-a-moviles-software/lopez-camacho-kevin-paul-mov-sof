package com.example.deber_02

class BBaseDatosMemoria {
    companion object{
        val arregloPublicaciones = arrayListOf<BPublicacion>()
        init{
            arregloPublicaciones.add(BPublicacion("epn_ecuador", "9 Me gusta", "🔴🔵🟠 MAESTRIAS EPN CONVOCATORIA 2023-B | loT es la tecnologia que permite la coneccion",
            "Hace 15 horas", R.drawable.perfil1, R.drawable.publicacion1))
            arregloPublicaciones.add(BPublicacion("momos_epn", "167 Me gusta", "Por que saco malas notas si me esfuerzo",
                "8 de julio", R.drawable.perfil2, R.drawable.publicacion2))
            arregloPublicaciones.add(BPublicacion("fis_epn_ec", "15 Me gusta", "El Programa de Doctorado en Informatica de la Facultad de Ingenieria de Sistemas le exitiende un cordial saludo",
                "Hace 4 dias", R.drawable.perfil3, R.drawable.publicacion3))
        }

        val arregloHistorias = arrayListOf<BHistoria>()
        init{
            arregloHistorias.add(BHistoria("Tu historia", R.drawable.perfil))
            arregloHistorias.add(BHistoria("epn_ecuador", R.drawable.perfil1))
            arregloHistorias.add(BHistoria("momos_epn", R.drawable.perfil2))
            arregloHistorias.add(BHistoria("fis_epn_ec", R.drawable.perfil3))
            arregloHistorias.add(BHistoria("mcdonalds_ecu", R.drawable.perfil4))
            arregloHistorias.add(BHistoria("kingsleague", R.drawable.perfil5))
            arregloHistorias.add(BHistoria("fepon.epn", R.drawable.perfil6))
            arregloHistorias.add(BHistoria("aeis_epn", R.drawable.perfil7))
            arregloHistorias.add(BHistoria("aepiqepn", R.drawable.perfil8))

        }
    }
}