class Modulo(val numAlumnos: Int = 20) {
    var alumnos = arrayOfNulls<Alumno?>(numAlumnos)
    var notas = Array(4) { FloatArray(numAlumnos)}

    companion object {
        const val EV_PRIMER = "0"
        const val EV_SEGUNDA = "1"
        const val EV_TERCERA = "2"
        const val EV_FINAL = "3"
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

    fun establecerNota(idAlumno: String, evaluacion: String, nota: Float) {
        notas[evaluacion.toInt()][nota.toInt()]
    }


}

class Alumno(val idAlumno: String, val nombre: String, val apellido1: String, val apellido2: String) {

}

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

    programacion.establecerNota("A123","0",5.0F)


    println(3)
}