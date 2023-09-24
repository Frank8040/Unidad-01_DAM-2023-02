package pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.asistenciaX

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc_mobile.models.Asistenciapa
import pe.edu.upeu.asistenciaupeujc_mobile.models.ComboModel
import pe.edu.upeu.asistenciaupeujc_mobile.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.components.form.TimePickerCustom

@Composable
fun AsistenciaXForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AsistenciaXFormViewModel = hiltViewModel()
) {
    val actividadD:Asistenciapa
    if (text!="0"){
        actividadD = Gson().fromJson(text, Asistenciapa::class.java)
    }else{
        actividadD= Asistenciapa(0,
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            0,
            "",
            0
        )
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(actividadD.id!!,
        darkMode,
        navController,
        actividadD,
        viewModel
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               actividad:Asistenciapa,
               viewModel: AsistenciaXFormViewModel
){

    Log.i("VERRR", "d: "+actividad?.id!!)
    val person = Asistenciapa(
        0,
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        0,
        "",
        0
    )
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latituda=lo.latitude.toString()
                person.longituda=lo.longitude.toString()
            }
        }
    }
    scope.launch{
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        Log.e("LATLON", "Lat: ${person.latituda} Lon: ${person.longituda}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = actividad?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = actividad?.horaReg!!, MyFormKeys.TIME, "HH:mm:ss")
                NameTextField(easyForms = easyForm, text =actividad?.tipo!!,"Tipo:", MyFormKeys.TIPO )
                NameTextField(easyForms = easyForm, text = actividad.calificacion.toString(), label = "Calificación:", key = MyFormKeys.CALIFICACION)
                NameTextField(easyForms = easyForm, text =actividad?.cui!!,"Tipo CUI:", MyFormKeys.CUI )
                NameTextField(easyForms = easyForm, text =actividad?.tipoCui!!,"Tipo CUI:", MyFormKeys.TIPOCUI )
                var listEv = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Entrada y Salida:", actividad.entsal, list =listEv, MyFormKeys.ENTSAL )
                NameTextField(easyForms = easyForm, text = actividad.subactasisId.toString(), label = "SubactasisId:", key = MyFormKeys.SUBACTASISID)
                DropdownMenuCustom(easyForm = easyForm, label = "Reg. Offline:", actividad.offlinex, list =listEv, MyFormKeys.OFFLINE )
                NameTextField(easyForms = easyForm, text =actividad?.actividadId!!.toString(),"actividadID:", MyFormKeys.ACTIVIDADID )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()

                        person.fecha=(lista.get(0) as EasyFormsResult.GenericStateResult<String>).value
                        person.horaReg=(lista.get(1) as EasyFormsResult.GenericStateResult<String>).value
                        person.tipo=((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.calificacion = (lista.get(3) as EasyFormsResult.GenericStateResult<String>).value.toLong()
                        person.cui=((lista.get(4) as EasyFormsResult.GenericStateResult<String>).value)
                        person.tipoCui=((lista.get(5) as EasyFormsResult.GenericStateResult<String>).value)
                        person.entsal= splitCadena((lista.get(6) as EasyFormsResult.GenericStateResult<String>).value)
                        person.subactasisId = (lista.get(7) as EasyFormsResult.GenericStateResult<String>).value.toLong()
                        person.offlinex= splitCadena((lista.get(8) as EasyFormsResult.GenericStateResult<String>).value)
                        person.actividadId = (lista.get(7) as EasyFormsResult.StringResult).value.toLong()

                        if (id==0.toLong()){
                            Log.i("AGREGAR", "ES:"+ person.entsal)
                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            Log.i("AGREGARID", "OF:"+ person.actividadId)
                            viewModel.addAsistenciaX(person)
                            navController.navigate(Destinations.AsistenciaXUI.route)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editAsistenciaX(person)
                            navController.navigate(Destinations.AsistenciaXUI.route)
                        }
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.AsistenciaXUI.route)
                    }
                }
            }
        }
    }
}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}