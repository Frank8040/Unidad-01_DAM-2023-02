package pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa

@Dao
interface AsistenciaXDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciaX(asistenciaX: Asistenciapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciasX(asistenciaX: List<Asistenciapa>)

    @Update
    suspend fun modificarAsistenciaX(asistenciaX: Asistenciapa)

    @Delete
    suspend fun eliminarAsistenciaX(asistenciaX: Asistenciapa)

    @Query("select * from asistenciaX")
    fun reportatAsistenciaX():LiveData<List<Asistenciapa>>

    @Query("select * from asistenciaX where id=:idx")
    fun buscarAsistenciaX(idx: Long):LiveData<Asistenciapa>

}