package pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.materialesx

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc_mobile.models.Actividad
import pe.edu.upeu.asistenciaupeujc_mobile.models.Materialesx
import pe.edu.upeu.asistenciaupeujc_mobile.models.MaterialesxConActividad
import pe.edu.upeu.asistenciaupeujc_mobile.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujc_mobile.repository.MaterialesxRepository
import javax.inject.Inject

@HiltViewModel
class MaterialesxViewModel @Inject constructor(
    private val matexRepo: MaterialesxRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    
    val activ: LiveData<List<MaterialesxConActividad>> by lazy {
        matexRepo.reportarMaterialesxes()
    }
    
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addMaterialesx() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteMaterialesx(toDelete: MaterialesxConActividad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            matexRepo.deleteMaterialesx(toDelete);
        }
    }

}