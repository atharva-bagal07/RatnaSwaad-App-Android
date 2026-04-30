//package com.example.ratnaswaad.navigation
//
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.ratnaswaad.ui.screens.OtpScreen
//import com.example.ratnaswaad.ui.screens.HomeScreen
//import com.example.ratnaswaad.ui.screens.LoginScreen
//import com.example.ratnaswaad.ui.screens.SignUpScreen
//
//
//@Composable
//fun Navigation() {
//
//    val navController = rememberNavController()
//
//
//    NavHost(
//        navController = navController,
//        startDestination = Routes.SignupScreenRoute.route
//    ) {
//        composable(route = Routes.SignupScreenRoute.route){
//            SignUpScreen{
//                (navController.navigate(route = Routes.LoginScreenRoute.route))
//            }
//        }
//
//        composable(route = Routes.LoginScreenRoute.route) {
//            LoginScreen {
//                navController.navigate(route = Routes.OtpScreenRoute.route)
//            }
//        }
//
//        composable(Routes.OtpScreenRoute.route) {
//            OtpScreen {
//                navController.navigate(route = Routes.HomeScreenRoute.route)
//
//            }
//        }
//        composable(Routes.HomeScreenRoute.route) {
//            HomeScreen()
//        }
//    }
//
//}


package com.example.ratnaswaad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ratnaswaad.ui.screens.HomeScreen
import com.example.ratnaswaad.ui.screens.LoginScreen
import com.example.ratnaswaad.ui.screens.OtpScreen
import com.example.ratnaswaad.ui.screens.SignUpScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreenRoute.route
    ) {

        composable(route = Routes.SignupScreenRoute.route) {
            SignUpScreen(
                goToLoginScreen = {
                    navController.navigate(Routes.LoginScreenRoute.route) {
                        popUpTo(Routes.SignupScreenRoute.route) { inclusive = true }
                    }
                },
                goToOtpScreen = { verificationId ->
                    navController.navigate(Routes.OtpScreenRoute.createRoute(verificationId))
                }
            )
        }

        composable(route = Routes.LoginScreenRoute.route) {
            LoginScreen(
                goToSignupScreen = {
                    navController.navigate(Routes.SignupScreenRoute.route)
                },
                goToOtpScreen = { verificationId ->
                    navController.navigate(Routes.OtpScreenRoute.createRoute(verificationId))
                }
            )
        }

        composable(
            route = Routes.OtpScreenRoute.route,
            arguments = listOf(
                navArgument("verificationId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val verificationId = backStackEntry.arguments?.getString("verificationId") ?: ""
            OtpScreen(
                verificationId = verificationId,
                goToHomeScreen = {
                    navController.navigate(Routes.HomeScreenRoute.route) {
                        // Clear the entire auth stack so back button can't go back to login
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HomeScreenRoute.route) {
            HomeScreen()
        }
    }
}
