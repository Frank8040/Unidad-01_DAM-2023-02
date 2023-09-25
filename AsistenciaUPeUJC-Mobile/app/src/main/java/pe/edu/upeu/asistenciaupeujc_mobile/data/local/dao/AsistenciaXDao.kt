package pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.models.AsistenciapaConActividad

@Dao
interface AsistenciaXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciaX(asistenciaX: Asistenciapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciasX(asistenciaX: List<Asistenciapa>)

    @Update
    suspend fun modificarAsistenciaX(asistenciaX: Asistenciapa)

    @Query("delete  from asistenciaX where id=:asistenciaX")
    suspend fun eliminarAsistenciaX(asistenciaX: Long)
    /*@Delete
    suspend fun eliminarMaterialesx(materialesx: Materialesx)

    @Query("select * from asistenciaX")
    fun reportatAsistenciaX():LiveData<List<Asistenciapa>>*/

    @Transaction
    @Query("select m.*, a.nombreActividad from asistenciaX m, actividad a where m.actividadId=a.id")
    fun reportatAsistenciaX():LiveData<List<AsistenciapaConActividad>>

    @Query("select * from asistenciaX where id=:idx")
    fun buscarAsistenciaX(idx: Long):LiveData<Asistenciapa>

}