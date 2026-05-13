package com.example.ratnaswaad.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.ratnaswaad.ui.screens.MangoProduct

data class CartItem(
    val product: MangoProduct,
    var quantity: Int
)

class CartViewModel : ViewModel() {

    // Live list of cart items — Compose observes this automatically
    val cartItems = mutableStateListOf<CartItem>()

    // ── Add or increment ───────────────────────────────────────────────
    fun addToCart(product: MangoProduct, quantity: Int) {
        val existing = cartItems.indexOfFirst { it.product.name == product.name }
        if (existing >= 0) {
            cartItems[existing] = cartItems[existing].copy(
                quantity = cartItems[existing].quantity + quantity
            )
        } else {
            cartItems.add(CartItem(product = product, quantity = quantity))
        }
    }

    // ── Increment one ──────────────────────────────────────────────────
    fun increment(product: MangoProduct) {
        val index = cartItems.indexOfFirst { it.product.name == product.name }
        if (index >= 0) {
            cartItems[index] = cartItems[index].copy(quantity = cartItems[index].quantity + 1)
        }
    }

    // ── Decrement one (removes if hits 0) ─────────────────────────────
    fun decrement(product: MangoProduct) {
        val index = cartItems.indexOfFirst { it.product.name == product.name }
        if (index >= 0) {
            if (cartItems[index].quantity <= 1) {
                cartItems.removeAt(index)
            } else {
                cartItems[index] = cartItems[index].copy(quantity = cartItems[index].quantity - 1)
            }
        }
    }

    // ── Remove entirely ────────────────────────────────────────────────
    fun remove(product: MangoProduct) {
        cartItems.removeAll { it.product.name == product.name }
    }

    // ── Clear after order placed ───────────────────────────────────────
    fun clearCart() {
        cartItems.clear()
    }

    // ── Totals ─────────────────────────────────────────────────────────
    val totalItems: Int get() = cartItems.sumOf { it.quantity }
    val totalPrice: Int get() = cartItems.sumOf { it.product.price * it.quantity }
    val totalMangoes: Int get() = cartItems.sumOf { it.product.pieces * it.quantity }
}