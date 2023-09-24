package pe.edu.upeu.asistenciaupeujc_mobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.AsistenciaXDao
import pe.edu.upeu.asistenciaupeujc_mobile.models.Actividad
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa

@Database(entities = [Actividad::class, Asistenciapa::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun asistenciaXDao():AsistenciaXDao
}
