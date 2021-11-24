class Modulo(val numAlumnos: Int = 20) {
    var alumnos = arrayOfNulls<Alumno?>(numAlumnos)
    var evaluaciones = Array(4) { FloatArray(numAlumnos) }

    companion object {
        const val EV_PRIMER = "0"
        const val EV_SEGUNDA = "1"
        const val EV_TERCERA = "2"
        const val EV_FINAL = "3"
    }

    fun establecerNota(idAlumno: String, evaluacion: String, nota: Float): Boolean {
        evaluaciones[evaluacion.toInt()][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] = nota
        return true
    }

    fun calculaEvaluacionFinal(idAlumno: String) {
        var evFinal =
            evaluaciones[0][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] + evaluaciones[1][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] + evaluaciones[2][alumnos.indexOfFirst { it?.idAlumno == idAlumno }]
        evFinal /= 3
        evaluaciones[3][alumnos.indexOfFirst { it?.idAlumno == idAlumno }] = evFinal

    }

    fun listaNotas(evaluacion: String): List<Pair<Alumno, Float>> {
        val listaNotas: MutableList<Pair<Alumno, Float>> = mutableListOf()
        if (evaluacion == "0" || evaluacion == "1" || evaluacion == "2" || evaluacion == "3") {
            for (i in 0 until this.numAlumnos) {
                if (alumnos[i] != null) listaNotas.add(Pair(alumnos[i]!!, evaluaciones[evaluacion.toInt()][i]))
            }
        }
        return listaNotas

    }

    fun numeroAprobados(evaluacion: String): Int {
        return evaluaciones[evaluacion.toInt()].count() { it > 4.99 }
    }

    fun notaMasBaja(evaluacion: String): Float {
        return evaluaciones[evaluacion.toInt()].filter { it > 0.0F }.minOrNull() ?: (-1.0F)
    }

    fun notaMasAlta(evaluacion: String): Float {
        return evaluaciones[evaluacion.toInt()].filter { it > 0.0F }.maxOrNull() ?: (-1.0F)
    }

    fun notaMedia(evaluacion: String): Float {
        var media = 0.0F
        var alumnosCalificados = 0
        if (evaluacion == "0" || evaluacion == "1" || evaluacion == "2" || evaluacion == "3") {
            for (i in alumnos.indices) {
                if (evaluaciones[evaluacion.toInt()][i] > 0.0F) {
                    media += evaluaciones[evaluacion.toInt()][i]
                    alumnosCalificados++
                }
            }
        }
        media /= alumnosCalificados
        return media
    }

    fun hayAlumnosConDiez(evaluacion: String): Boolean {
        return evaluaciones[evaluacion.toInt()].any { it == 10.0F }
    }

    fun hayAlumnosAprobados(evaluacion: String): Boolean {
        return evaluaciones[evaluacion.toInt()].any { it >= 5.0F }
    }

    fun primeraNotaNoAprobada(evaluacion: String): Float {
        return evaluaciones[evaluacion.toInt()][evaluaciones[evaluacion.toInt()].indexOfFirst { (it < 5.0F) && (it >= 0.0F) }]
    }


    fun listaNotasOrdenados(evaluacion: String): List<Pair<Alumno, Float>> {
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
    val alumno10 = Alumno("J759", "Marina", "Perez", "Rodriguez")

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

    programacion.establecerNota("A123", "0", 4.0F)
    programacion.establecerNota("A123", "1", 7.0F)
    programacion.establecerNota("B456", "0", 9.5F)
    programacion.establecerNota("B456", "1", 5.0F)
    programacion.establecerNota("B456", "2", 10.0F)

    programacion.calculaEvaluacionFinal("A123")
    programacion.calculaEvaluacionFinal("B456")

    println("La primera nota suspensa encontrada es un ${programacion.primeraNotaNoAprobada("0")}")
    println("En la primera evaluación hay ${programacion.numeroAprobados("0")} alumnos aprobados.")
    println("La nota más baja de la segunda evaluación es un ${programacion.notaMasBaja("1")}")
    println("La nota más alta de la primera evaluación es un ${programacion.notaMasAlta("0")}")
    println("La nota media de la primera evaluación es un ${programacion.notaMedia("0")}")
    repeat(80) { print("*") }
    println()
    println("La lista de notas de la segunda evaluación es:")
    println(programacion.listaNotasOrdenados("1"))
    repeat(80) { print("*") }
    println()


}