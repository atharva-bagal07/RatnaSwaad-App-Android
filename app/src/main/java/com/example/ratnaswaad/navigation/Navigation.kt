package com.example.ratnaswaad.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ratnaswaad.ui.screens.HomeScreen
import com.example.ratnaswaad.ui.screens.LoginScreen
import com.example.ratnaswaad.ui.screens.OtpScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreenRoute.route
    ) {

        composable(route = Routes.LoginScreenRoute.route) {
            LoginScreen {
                navController.navigate(route = Routes.OtpScreenRoute.route)
            }
        }

        composable(Routes.OtpScreenRoute.route) {
//            OtpScreen{
//                navController.navigate(route = Routes.HomeScreenRoute.route)
//
//            }
        }
        composable(Routes.HomeScreenRoute.route) {
            HomeScreen()
        }
    }

}
