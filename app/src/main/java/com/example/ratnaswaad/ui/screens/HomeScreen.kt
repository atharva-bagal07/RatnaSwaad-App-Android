package com.example.ratnaswaad.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratnaswaad.R

// ── Brand tokens ───────────────────────────────────────────────────────────────
private val Green = Color(0xFF245F35)
private val Amber = Color(0xFFFFBD25)
private val Parchment = Color(0xFFFCF9F1)
private val CardWhite = Color(0xFFFCFBF6)
private val InputBg = Color(0xFFF6F3EB)
private val TextDark = Color(0xFF1C1C17)
private val TextMuted = Color(0xFF7A7A6E)

val PoppinsBold = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold))
private val Pacifico = FontFamily(Font(R.font.pacifico_regular))

// ── Data ───────────────────────────────────────────────────────────────────────
data class MangoProduct(
    val name: String,
    val description: String,
    val pieces: Int,
    val price: Int,
    val isBestSeller: Boolean = false,
    val imageRes: Int
)

val mangoProducts = listOf(
    MangoProduct(
        name = "Family Pack",
        description = "Perfect for small families or first-time buyers",
        pieces = 6,
        price = 400,
        imageRes = R.drawable.family_pack   // replace with your actual drawable name
    ),
    MangoProduct(
        name = "Celebration Pack",
        description = "Ideal for sharing and family gatherings",
        pieces = 12,
        price = 800,
        isBestSeller = true,
        imageRes = R.drawable.celebration_pack
    ),
    MangoProduct(
        name = "Large Order",
        description = "Best suited for events and larger requirements",
        pieces = 24,
        price = 1600,
        imageRes = R.drawable.large_order
    )
)

// ── Screen ─────────────────────────────────────────────────────────────────────
@Composable
fun HomeScreen(
    onProductClick: (MangoProduct) -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Parchment)
            .verticalScroll(rememberScrollState())
    ) {

        // ── Top Bar ────────────────────────────────────────────────────
        TopBar(onCartClick = onCartClick, onProfileClick = onProfileClick)

        // ── Hero Banner ────────────────────────────────────────────────
        HeroBanner()

        // ── Section Title ──────────────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            Text(
                text = "Our Boxes",
                fontSize = 22.sp,
                fontFamily = PoppinsBold,
                color = Green
            )
            Text(
                text = "Ratnagiri Alphonso · Farm fresh · Delivered to your door",
                fontSize = 12.sp,
                fontFamily = PoppinsBold,
                color = TextMuted
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ── Product Cards ──────────────────────────────────────────────
        mangoProducts.forEach { product ->
            ProductCard(
                product = product,
                onSelectClick = { onProductClick(product) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── Footer note ────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🥭  All mangoes are handpicked from Ratnagiri orchards\nand delivered within 2–3 days across Mumbai & Pune.",
                fontSize = 12.sp,
                fontFamily = PoppinsBold,
                color = TextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ── Top Bar ────────────────────────────────────────────────────────────────────
@Composable
private fun TopBar(
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardWhite)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo + name
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ratna_logo),
                contentDescription = "RatnaSwaad Logo",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "RatnaSwaad",
                    fontSize = 18.sp,
                    fontFamily = PoppinsBold,
                    color = Green
                )
                Text(
                    text = "Grown with care, shared with love.",
                    fontSize = 10.sp,
                    fontFamily = Pacifico,
                    color = Color(0xFF714B26)
                )
            }
        }

        // Action icons
        Row {
            IconButton(onClick = onCartClick) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Green,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Green,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// ── Hero Banner ────────────────────────────────────────────────────────────────
@Composable
private fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF1A4A28), Color(0xFF245F35), Color(0xFF2E7A45))
                )
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "Season's Best 🥭",
                fontSize = 24.sp,
                fontFamily = PoppinsBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ratnagiri Alphonso Mangoes",
                fontSize = 14.sp,
                fontFamily = PoppinsBold,
                color = Color.White.copy(alpha = 0.85f)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .background(Amber, RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "₹800 / dozen",
                    fontSize = 13.sp,
                    fontFamily = PoppinsBold,
                    color = Green
                )
            }
        }

        // Decorative circle
        Box(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterEnd)
                .background(
                    color = Color.White.copy(alpha = 0.07f),
                    shape = CircleShape
                )
        )
    }
}

// ── Product Card ───────────────────────────────────────────────────────────────
@Composable
private fun ProductCard(
    product: MangoProduct,
    onSelectClick: () -> Unit
) {
    val borderColor = if (product.isBestSeller) Amber else Color.Transparent
    val buttonColor = if (product.isBestSeller) Amber else Green
    val buttonTextColor = if (product.isBestSeller) Green else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .then(
                if (product.isBestSeller)
                    Modifier.border(2.dp, Amber, RoundedCornerShape(16.dp))
                else Modifier
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Best seller badge
            if (product.isBestSeller) {
                Box(
                    modifier = Modifier
                        .background(Amber, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "⭐  BEST SELLER",
                        fontSize = 11.sp,
                        fontFamily = PoppinsBold,
                        color = Green
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Piece count badge + image row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Piece count badge
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            color = if (product.isBestSeller) Amber else InputBg,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${product.pieces}",
                            fontSize = 22.sp,
                            fontFamily = PoppinsBold,
                            color = if (product.isBestSeller) Green else TextDark
                        )
                        Text(
                            text = "Pcs",
                            fontSize = 11.sp,
                            fontFamily = PoppinsBold,
                            color = if (product.isBestSeller) Green else TextMuted
                        )
                    }
                }

                // Product image placeholder
                // Replace Box below with Image() once you have actual drawables
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(InputBg, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                // Price
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "₹${product.price}",
                        fontSize = 24.sp,
                        fontFamily = PoppinsBold,
                        color = Green
                    )
                    Text(
                        text = "incl. delivery",
                        fontSize = 10.sp,
                        fontFamily = PoppinsBold,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Name + description
            Text(
                text = product.name,
                fontSize = 18.sp,
                fontFamily = PoppinsBold,
                color = if (product.isBestSeller) Amber else Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.description,
                fontSize = 12.sp,
                fontFamily = PoppinsBold,
                color = TextMuted,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CTA button
            Button(
                onClick = onSelectClick,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "Select Box",
                    fontSize = 15.sp,
                    fontFamily = PoppinsBold,
                    color = buttonTextColor
                )
            }
        }
    }
}