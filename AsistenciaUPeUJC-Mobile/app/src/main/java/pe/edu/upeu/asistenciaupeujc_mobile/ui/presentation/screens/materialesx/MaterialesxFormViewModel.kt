package pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.materialesx

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc_mobile.models.Actividad
import pe.edu.upeu.asistenciaupeujc_mobile.models.Materialesx
import pe.edu.upeu.asistenciaupeujc_mobile.models.MaterialesxReport
import pe.edu.upeu.asistenciaupeujc_mobile.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujc_mobile.repository.MaterialesxRepository
import javax.inject.Inject

@HiltViewModel
class MaterialesxFormViewModel @Inject constructor(
    private val materRepo: MaterialesxRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getMaterialesx(idX: Long): LiveData<Materialesx> {
        return materRepo.buscarMaterialesxId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addMaterialesx(materialesx: Materialesx){
        viewModelScope.launch (Dispatchers.IO){
            try {
                materRepo.insertarMaterialesx(materialesx)
            }catch (e:Exception){
                Log.i("ERRRRR", "${e.message}")
            }
        }
    }
    
    fun editMaterialesx(materialesx: Materialesx){
        viewModelScope.launch(Dispatchers.IO){
            try {
                materRepo.modificarRemoteMaterialesx(materialesx)
            }catch (e:Exception){
                Log.i("ERRRRREDI", "${e.message}")
            }
        }
    }
}