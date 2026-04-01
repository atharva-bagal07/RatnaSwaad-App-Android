package com.example.ratnaswaad.navigation

sealed class Routes(val route: String) {
    data object SignupScreenRoute : Routes("SignupScreen")
    data object LoginScreenRoute : Routes("LoginScreen")
    data object OtpScreenRoute : Routes("OtpScreen")
    data object HomeScreenRoute : Routes("HomeScreen")
}