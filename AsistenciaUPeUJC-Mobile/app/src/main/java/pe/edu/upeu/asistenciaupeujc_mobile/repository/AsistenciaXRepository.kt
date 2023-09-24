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
    private val restAsistenciaX: RestAsistenciaX,
    private val asistenciaXDao: AsistenciaXDao
): AsistenciaXRepository{
    override suspend fun deleteAsistenciaX(asistenciaX: Asistenciapa){
        CoroutineScope(Dispatchers.IO).launch {
            restAsistenciaX.deleteAsistenciaX(TokenUtils.TOKEN_CONTENT,asistenciaX.id)
        }
        asistenciaXDao.eliminarAsistenciaX(asistenciaX)
    }

    override fun reportarAsistenciasX():LiveData<List<Asistenciapa>>{
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restAsistenciaX.reportarAsistenciaX(TokenUtils.TOKEN_CONTENT).body()!!
                asistenciaXDao.insertarAsistenciasX(data)
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return asistenciaXDao.reportatAsistenciaX()
    }

    override fun buscarAsistenciaXId(id:Long):LiveData<Asistenciapa>{
        return  asistenciaXDao.buscarAsistenciaX(id)
    }

    override suspend fun insertarAsistenciaX(asistenciaX: Asistenciapa):Boolean{
        return restAsistenciaX.insertarAsistenciaX(TokenUtils.TOKEN_CONTENT, asistenciaX).body()!=null
    }

    override suspend fun modificarRemoteAsistenciaX(asistenciaX: Asistenciapa):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restAsistenciaX.actualizarAsistenciaX(TokenUtils.TOKEN_CONTENT, asistenciaX.id, asistenciaX).body()!=null
    }

}