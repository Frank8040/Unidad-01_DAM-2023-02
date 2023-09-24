package pe.edu.upeu.asistenciaupeujc_mobile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.AsistenciaXDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestAsistenciaX
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.utils.TokenUtils
import javax.inject.Inject

interface AsistenciaXRepository {
    suspend fun deleteAsistenciaX(asistenciaX: Asistenciapa)
    fun reportarAsistenciasX():LiveData<List<Asistenciapa>>

    fun buscarAsistenciaXId(id:Long):LiveData<Asistenciapa>

    suspend fun insertarAsistenciaX(asistenciaX: Asistenciapa):Boolean

    suspend fun modificarRemoteAsistenciaX(asistenciaX: Asistenciapa):Boolean
}

class AsistenciaXRepositoryImp @Inject constructor(
    private val restActividad: RestAsistenciaX,
    private val actividadDao: AsistenciaXDao
): AsistenciaXRepository{
    override suspend fun deleteAsistenciaX(asistenciaX: Asistenciapa){
        CoroutineScope(Dispatchers.IO).launch {
            restActividad.deleteAsistenciaX(TokenUtils.TOKEN_CONTENT,asistenciaX.id)
        }
        actividadDao.eliminarAsistenciaX(asistenciaX)
    }

    override fun reportarAsistenciasX():LiveData<List<Asistenciapa>>{
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restActividad.reportarAsistenciaX(TokenUtils.TOKEN_CONTENT).body()!!
                actividadDao.insertarAsistenciasX(data)
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return actividadDao.reportatAsistenciaX()
    }

    override fun buscarAsistenciaXId(id:Long):LiveData<Asistenciapa>{
        return  actividadDao.buscarAsistenciaX(id)
    }

    override suspend fun insertarAsistenciaX(asistenciaX: Asistenciapa):Boolean{
        return restActividad.insertarAsistenciaX(TokenUtils.TOKEN_CONTENT, asistenciaX).body()!=null
    }

    override suspend fun modificarRemoteAsistenciaX(asistenciaX: Asistenciapa):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restActividad.actualizarAsistenciaX(TokenUtils.TOKEN_CONTENT, asistenciaX.id, asistenciaX).body()!=null
    }

}