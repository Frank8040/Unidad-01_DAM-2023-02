package pe.edu.upeu.asistenciaupeujc_mobile.data.remote

import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.models.AsistenciapaReport
import pe.edu.upeu.asistenciaupeujc_mobile.models.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAsistenciaX {
    @GET("/asis/asistenciaX/list")
    suspend fun reportarAsistenciaX(@Header("Authorization") token:String):Response<List<AsistenciapaReport>>

    @GET("/asis/asistenciaX/buscar/{id}")
    suspend fun getAsistenciaXId(@Header("Authorization") token:String, @Query("id") id:Long):Response<Asistenciapa>

    @DELETE("/asis/asistenciaX/eliminar/{id}")
    suspend fun deleteAsistenciaX(@Header("Authorization") token:String, @Path("id") id:Long):Response<MsgGeneric>

    @PUT("/asis/asistenciaX/editar/{id}")
    suspend fun actualizarAsistenciaX(@Header("Authorization") token:String, @Path("id") id:Long, @Body asistenciaX: Asistenciapa): Response<Asistenciapa>

    @POST("/asis/asistenciaX/crear")
    suspend fun insertarAsistenciaX(@Header("Authorization") token:String,@Body asistenciaX: Asistenciapa): Response<Asistenciapa>
}