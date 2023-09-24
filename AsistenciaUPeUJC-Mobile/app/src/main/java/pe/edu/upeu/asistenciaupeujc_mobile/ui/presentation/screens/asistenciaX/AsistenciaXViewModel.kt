package pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.asistenciaX

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.repository.AsistenciaXRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaXViewModel @Inject constructor(
    private val activRepo: AsistenciaXRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<Asistenciapa>> by lazy {
        activRepo.reportarAsistenciasX()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addAsistenciaX() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteAsistenciaX(toDelete: Asistenciapa) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMINAR", toDelete.toString())
            activRepo.deleteAsistenciaX(toDelete);
        }
    }

}