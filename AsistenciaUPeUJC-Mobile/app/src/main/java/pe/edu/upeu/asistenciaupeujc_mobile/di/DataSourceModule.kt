package pe.edu.upeu.asistenciaupeujc_mobile.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.DbDataSource
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.AsistenciaXDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestActividad
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestAsistenciaX
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestMaterialesx
import pe.edu.upeu.asistenciaupeujc_mobile.data.remote.RestUsuario
import pe.edu.upeu.asistenciaupeujc_mobile.utils.TokenUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    var retrofit:Retrofit?=null

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl()=TokenUtils.API_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl:String):Retrofit{
        val okHttpClient=OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        if (retrofit==null){
            retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl).build()
        }
        return retrofit!!
    }

    @Singleton
    @Provides
    fun restUsuario(retrofit: Retrofit):RestUsuario{
        return retrofit.create(RestUsuario::class.java)
    }

    @Singleton
    @Provides
    fun restActividad(retrofit: Retrofit):RestActividad{
        return retrofit.create(RestActividad::class.java)
    }

    @Singleton
    @Provides
    fun restAsistenciaX(retrofit: Retrofit):RestAsistenciaX{
        return retrofit.create(RestAsistenciaX::class.java)
    }

    @Singleton
    @Provides
    fun restMaterialesx(retrofit: Retrofit): RestMaterialesx {
        return retrofit.create(RestMaterialesx::class.java)
    }

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context:Context):DbDataSource{
        return Room.databaseBuilder(context, DbDataSource::class.java, "eventoasistencia_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun actividadDao(db:DbDataSource):ActividadDao{
        return db.actividadDao()
    }

    @Singleton
    @Provides
    fun asistenciaXDao(db:DbDataSource):AsistenciaXDao{
        return db.asistenciaXDao()
    }

    @Singleton
    @Provides
    fun materialesxDao(db:DbDataSource): MaterialesxDao {
        return db.materialesxDao()
    }

}