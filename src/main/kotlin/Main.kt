class Modulo(val numAlumnos: Int = 20) {
    var alumnos = arrayOfNulls<Alumno?>(numAlumnos)
    var notas = Array(4) { FloatArray(numAlumnos) }

    companion object {
        const val EV_PRIMER = "0"
        const val EV_SEGUNDA = "1"
        const val EV_TERCERA = "2"
        const val EV_FINAL = "3"
    }

    fun establecerNota(idAlumno: String, evaluacion: String, nota: Float): Boolean {
        notas[evaluacion.toInt()][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] = nota
        return true
    }

    fun calculaEvaluacionFinal(idAlumno: String) {
        var evFinal =
            notas[0][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] + notas[1][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] + notas[2][alumnos.indexOfFirst { it?.idAlumno == idAlumno }]
        evFinal /= 3
        notas[3][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] = evFinal

    }

    fun listaNotas(evaluacion: String): List<Pair<String,Float>> {

    }
    fun numeroAprobados(evaluacion: String): Int {
        return notas[evaluacion.toInt()].count() { it > 4.99 }
    }

    fun notaMasBaja(evaluacion: String): Float {
        return notas[evaluacion.toInt()].filter { it > 0.0F }.minOrNull() ?: (-1.0F)
    }

    fun notaMasAlta(evaluacion: String): Float {
        return notas[evaluacion.toInt()].filter { it > 0.0F }.maxOrNull() ?: (-1.0F)
    }

    fun notaMedia(evaluacion: String): Float {
        return
    }
    fun hayAlumnosConDiez(evaluacion: String): Boolean {
        return notas[evaluacion.toInt()].any { it == 10.0F }
    }

    fun hayAlumnosAprobados(evaluacion: String): Boolean {
        return notas[evaluacion.toInt()].any { it >= 5.0F }
    }

    fun primeraNotaNoAprobada(evaluacion: String): Float {
        return notas[evaluacion.toInt()][notas[evaluacion.toInt()].indexOfFirst { (it < 5.0F) && (it >= 0.0F) }]
    }


    fun listaNotasOrdenados(evaluacion: String): List<Pair<String,Float>> {
        return listaNotas(evaluacion).sortedBy { it.second }
    }

    var contador = 0
    fun matricularAlumno(alumno: Alumno): Boolean {
        if (contador < numAlumnos) {
            alumnos[contador] = alumno
            contador++
        }
        return true
    }

    fun bajaAlumno(idAlumno: String): Boolean {
        for (i in 0..10) {
            if (alumnos[i]?.idAlumno == idAlumno) {
                alumnos[i] = null
                return true
            }
        }
        return false
    }


}

data class Alumno(val idAlumno: String, val nombre: String, val apellido1: String, val apellido2: String)

fun main() {
    val programacion = Modulo(15)
    val alumno1 = Alumno("A123", "Ana", "Perez", "Gómez")
    val alumno2 = Alumno("B456", "Jesús", "Calvellido", "Toro")
    val alumno3 = Alumno("C789", "María", "Torres", "Morales")
    val alumno4 = Alumno("D147", "Yolanda", "Vargas", "Naranjo")
    val alumno5 = Alumno("E258", "Pedro", "López", "Cruz")
    val alumno6 = Alumno("F369", "Luis", "Borrego", "Toro")
    val alumno7 = Alumno("G159", "Pablo", "Rodriguez", "Martinez")
    val alumno8 = Alumno("H753", "Lola", "Gómez", "Borrego")
    val alumno9 = Alumno("I486", "Isabel", "Torres", "Chacón")
    val alumno10 = Alumno("J759", "Marina", "Pavón", "Rodriguez")

    programacion.matricularAlumno(alumno1)
    programacion.matricularAlumno(alumno2)
    programacion.matricularAlumno(alumno3)
    programacion.matricularAlumno(alumno4)
    programacion.matricularAlumno(alumno5)
    programacion.matricularAlumno(alumno6)
    programacion.matricularAlumno(alumno7)
    programacion.matricularAlumno(alumno8)
    programacion.matricularAlumno(alumno9)
    programacion.matricularAlumno(alumno10)

    programacion.establecerNota("A123", "0", 5.0F)

}