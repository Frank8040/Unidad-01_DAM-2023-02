package pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.asistenciaX

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.repository.AsistenciaXRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaXFormViewModel @Inject constructor(
    private val activRepo: AsistenciaXRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getActividad(idX: Long): LiveData<Asistenciapa> {
        return activRepo.buscarAsistenciaXId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addActividad(asistenciaX: Asistenciapa){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", asistenciaX.toString())
            activRepo.insertarAsistenciaX(asistenciaX)
        }
    }
    
    fun editActividad(asistenciaX: Asistenciapa){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteAsistenciaX(asistenciaX)
        }
    }
}