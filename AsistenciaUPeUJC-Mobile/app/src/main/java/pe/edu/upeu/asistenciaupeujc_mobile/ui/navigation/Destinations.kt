package pe.edu.upeu.asistenciaupeujc_mobile.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Login:Destinations("login", "Login", Icons.Filled.Settings)
    object Pantalla1 : Destinations( "pantalla1", "Pantalla 1", Icons.Filled.Home )
    object Pantalla2 : Destinations("pantalla2/?newText={newText}", "Pantalla 2", Icons.Filled.Settings) {
        fun createRoute(newText: String) = "pantalla2/?newText=$newText"
    }
    object Pantalla3 : Destinations("pantalla3", "Pantalla 3", Icons.Filled.Favorite)
    object Pantalla4 : Destinations("pantalla4", "Pantalla 4x", Icons.Filled.Face )

    object Pantalla5 : Destinations( "pantalla5", "Perfil", Icons.Filled.AccountBox )

    object ActividadUI: Destinations("actividadUI","Adm. Actividades", Icons.Filled.DateRange)

    object ActividadForm: Destinations("actividadForm?actId={actId}", "Form Actividad", Icons.Filled.Add){
        fun passId(actId:String?):String{
            return "actividadForm?actId=$actId"
        }
    }

    object AsistenciaXUI: Destinations("asistenciaXUI","Adm. Asistencias", Icons.Filled.List)

    object AsistenciaXForm: Destinations("asistenciaXForm?actId={actId}", "Form Asistencia", Icons.Filled.Add){
        fun passId(actId:String?):String{
            return "asistenciaXForm?actId=$actId"
        }
    }
}