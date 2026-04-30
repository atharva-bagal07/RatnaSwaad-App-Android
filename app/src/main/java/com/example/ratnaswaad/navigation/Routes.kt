package com.example.ratnaswaad.navigation

sealed class Routes(val route: String) {
    data object SignupScreenRoute : Routes("SignupScreen")
    data object LoginScreenRoute : Routes("LoginScreen")

    // verificationId is passed as a nav argument
    data object OtpScreenRoute : Routes("OtpScreen/{verificationId}") {
        fun createRoute(verificationId: String) = "OtpScreen/$verificationId"
    }

    data object HomeScreenRoute : Routes("HomeScreen")
}