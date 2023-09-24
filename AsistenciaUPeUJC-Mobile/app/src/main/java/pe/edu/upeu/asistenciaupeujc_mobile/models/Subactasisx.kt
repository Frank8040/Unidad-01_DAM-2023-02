package pe.edu.upeu.asistenciaupeujc_mobile.models

data class Subactasisx(
    var id: Long,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: Long,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String,
    var actividadId: String,
)