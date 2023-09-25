package pe.edu.upeu.asistenciaupeujc_mobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.AsistenciaXDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujc_mobile.models.Actividad
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.models.Materialesx

@Database(entities = [Actividad::class, Asistenciapa::class, Materialesx::class], version = 3)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun asistenciaXDao():AsistenciaXDao
    abstract fun materialesxDao(): MaterialesxDao
}
