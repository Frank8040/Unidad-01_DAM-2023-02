package pe.edu.upeu.asistenciaupeujc_mobile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestActividad
import pe.edu.upeu.asistenciaupeujc_mobile.models.Actividad
import pe.edu.upeu.asistenciaupeujc_mobile.utils.TokenUtils
import javax.inject.Inject

interface ActividadRepository {
    suspend fun deleteActividad(actividad: Actividad)
    
    fun reportarActividades():LiveData<List<Actividad>>

    fun buscarActividadId(id:Long):LiveData<Actividad>

    suspend fun insertarActividad(actividad: Actividad):Boolean

    suspend fun modificarRemoteActividad(actividad: Actividad):Boolean
}

class ActividadRepositoryImp @Inject constructor(
    private val restActividad: RestActividad,
    private val actividadDao: ActividadDao
): ActividadRepository{
    override suspend fun deleteActividad(actividad: Actividad){
        CoroutineScope(Dispatchers.IO).launch {
            restActividad.deleteActividad(TokenUtils.TOKEN_CONTENT,actividad.id)
        }
        actividadDao.eliminarActividad(actividad)
    }

    override fun reportarActividades(): LiveData<List<Actividad>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                delay(3000)
                val response = restActividad.reportarActividad(TokenUtils.TOKEN_CONTENT)
                if (response.isSuccessful) {
                    val data = response.body()!!
                    actividadDao.insertarActividades(data)
                } else {
                    // Manejo de errores de la solicitud a la API
                    Log.e("ERROR", "Error en la solicitud: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                // Manejo de excepciones generales
                Log.e("ERROR", "Error: ${e.message}")
            }
        }
        return actividadDao.reportatActividad()
    }


    override fun buscarActividadId(id:Long):LiveData<Actividad>{
        return  actividadDao.buscarActividad(id)
    }


    override suspend fun insertarActividad(actividad: Actividad): Boolean? {
        return restActividad.insertarActividad(TokenUtils.TOKEN_CONTENT, actividad).body()
    }

    override suspend fun modificarRemoteActividad(actividad: Actividad): Boolean? {
        return restActividad.actualizarActividad(TokenUtils.TOKEN_CONTENT, actividad.id, actividad).body()
    }
}