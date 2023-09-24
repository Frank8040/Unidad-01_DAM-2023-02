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
    private val asistRepo: AsistenciaXRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getAsistenciaX(idX: Long): LiveData<Asistenciapa> {
        return asistRepo.buscarAsistenciaXId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addAsistenciaX(asistenciaX: Asistenciapa){
        viewModelScope.launch (Dispatchers.IO){
            try {
                asistRepo.insertarAsistenciaX(asistenciaX)
            }catch (e:Exception){
                Log.i("Error", "${e.message}")
            }
        }
    }
    
    fun editAsistenciaX(asistenciaX: Asistenciapa){
        viewModelScope.launch(Dispatchers.IO){
            try {
                asistRepo.modificarRemoteAsistenciaX(asistenciaX)
            }catch (e:Exception){
                Log.i("Error", "${e.message}")
            }
        }
    }
}