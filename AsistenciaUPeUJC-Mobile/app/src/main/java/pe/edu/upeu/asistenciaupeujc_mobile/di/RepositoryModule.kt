package pe.edu.upeu.asistenciaupeujc_mobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.asistenciaupeujc_mobile.repository.AsistenciaXRepository
import pe.edu.upeu.asistenciaupeujc_mobile.repository.AsistenciaXRepositoryImp
import pe.edu.upeu.asistenciaupeujc_mobile.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujc_mobile.repository.ActividadRepositoryImp
import pe.edu.upeu.asistenciaupeujc_mobile.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujc_mobile.repository.UsuarioRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun userRepository(userRepos:UsuarioRepositoryImp):UsuarioRepository

    @Binds
    @Singleton
    abstract fun actividadRepository(actRepos:ActividadRepositoryImp):ActividadRepository

    @Binds
    @Singleton
    abstract fun asistenciaXRepository(actRepos:AsistenciaXRepositoryImp):AsistenciaXRepository

}