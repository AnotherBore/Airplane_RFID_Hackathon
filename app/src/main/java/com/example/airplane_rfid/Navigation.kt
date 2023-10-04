package com.example.airplane_rfid

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.airplane_rfid.screens.FlightList
import com.example.airplane_rfid.screens.ConcreteFlightScreen
import com.example.airplane_rfid.screens.SearchFlightsScreen

//просто навигация с контроллером, с главного окна поиска переход на схему борта конкретного самолета
@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchFlightsScreen(navController)
        }
        composable("flight/{airplaneNumber}", arguments = listOf(navArgument("airplaneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val airplaneNumber = backStackEntry.arguments?.getString("airplaneNumber")
            val selectedAirplane = FlightList.flights.find { it.airplaneNumber == airplaneNumber }
            if (selectedAirplane != null) ConcreteFlightScreen(selectedAirplane)
        }
    }
}