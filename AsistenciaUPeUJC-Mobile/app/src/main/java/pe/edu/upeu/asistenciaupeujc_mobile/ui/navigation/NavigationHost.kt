package pe.edu.upeu.asistenciaupeujc_mobile.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.Pantalla1
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.Pantalla2
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.Pantalla3
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.Pantalla4
import pe.edu.upeu.asistenciaupeujc_mobile.ui.presentation.screens.Pantalla5

@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {

    NavHost(
        navController = navController, startDestination = Destinations.Pantalla1.route
    ) {
        composable(Destinations.Pantalla1.route) {
            Pantalla1(
                navegarPantalla2 = { newText ->navController.navigate(Destinations.Pantalla2.createRoute(newText))
                }
            )
        }
        composable( Destinations.Pantalla2.route,
            arguments = listOf(navArgument("newText") {
                defaultValue = "Pantalla 2"
            })
        ) { navBackStackEntry ->
            var newText =
                navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            Pantalla2(newText, darkMode)
        }
        composable(Destinations.Pantalla3.route) { Pantalla3() }
        composable(Destinations.Pantalla4.route) { Pantalla4() }

        composable(Destinations.Pantalla5.route) {
            Pantalla5(
                navegarPantalla2 = { newText ->navController.navigate(Destinations.Pantalla2.createRoute(newText))
                },
                modifier = Modifier.padding().fillMaxSize().fillMaxWidth()
            )
        }
    }
}