package pe.edu.upeu.asistenciaupeujc_mobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistenciaX")
data class Asistenciapa(
    @PrimaryKey(autoGenerate = true)
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
    var actividadId: Long,
)

data class AsistenciapaConActividad(
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
    var actividadId: Long,
    var nombreActividad: String
)

data class AsistenciapaReport(
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
    var actividadId: Actividad
)