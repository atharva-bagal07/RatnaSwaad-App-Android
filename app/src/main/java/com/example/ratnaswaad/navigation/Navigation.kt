package com.example.ratnaswaad.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ratnaswaad.ui.screens.CartScreen
import com.example.ratnaswaad.ui.screens.HomeScreen
import com.example.ratnaswaad.ui.screens.LoginScreen
import com.example.ratnaswaad.ui.screens.OtpScreen
import com.example.ratnaswaad.ui.screens.ProductDetailScreen
import com.example.ratnaswaad.ui.screens.SignUpScreen
import com.example.ratnaswaad.ui.screens.mangoProducts
import com.example.ratnaswaad.ui.viewmodel.CartViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

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
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HomeScreenRoute.route) {
            HomeScreen(
                onProductClick = { product ->
                    val index = mangoProducts.indexOf(product)
                    navController.navigate(Routes.ProductDetailRoute.createRoute(index))
                },
                onCartClick = {
                    navController.navigate(Routes.CartRoute.route)
                },
                onProfileClick = { /* coming soon */ }
            )
        }

        composable(
            route = Routes.ProductDetailRoute.route,
            arguments = listOf(
                navArgument("productIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("productIndex") ?: 0
            val product = mangoProducts[index]
            ProductDetailScreen(
                product = product,
                onBack = { navController.popBackStack() },
                onAddToCart = { p, qty ->
                    cartViewModel.addToCart(p, qty)
                    navController.navigate(Routes.CartRoute.route)
                }
            )
        }

        composable(Routes.CartRoute.route) {
            CartScreen(
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onOrderPlaced = {
                    cartViewModel.clearCart()
                    navController.navigate(Routes.HomeScreenRoute.route) {
                        popUpTo(Routes.HomeScreenRoute.route) { inclusive = true }
                    }
                }
            )
        }
    }
}