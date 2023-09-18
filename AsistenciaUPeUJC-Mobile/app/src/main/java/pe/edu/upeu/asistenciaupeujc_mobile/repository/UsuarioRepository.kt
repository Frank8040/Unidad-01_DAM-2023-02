package pe.edu.upeu.asistenciaupeujc_mobile.repository

import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestUsuario
import pe.edu.upeu.asistenciaupeujc_mobile.models.UsuarioDto
import pe.edu.upeu.asistenciaupeujc_mobile.models.UsuarioResp
import retrofit2.Response
import javax.inject.Inject

interface UsuarioRepository {
    suspend fun loginUsuario(user:UsuarioDto): Response<UsuarioResp>
}

class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario):UsuarioRepository{
    override suspend fun loginUsuario(user:UsuarioDto): Response<UsuarioResp>{
        return restUsuario.login(user)
    }
}