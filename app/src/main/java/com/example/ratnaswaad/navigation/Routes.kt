package com.example.ratnaswaad.navigation

sealed class Routes(val route: String) {
    data object SignupScreenRoute : Routes("SignupScreen")
    data object LoginScreenRoute : Routes("LoginScreen")
    data object OtpScreenRoute : Routes("OtpScreen/{verificationId}") {
        fun createRoute(verificationId: String) = "OtpScreen/$verificationId"
    }

    data object HomeScreenRoute : Routes("HomeScreen")
    data object ProductDetailRoute : Routes("ProductDetail/{productIndex}") {
        fun createRoute(index: Int) = "ProductDetail/$index"
    }

    data object CartRoute : Routes("Cart")
}