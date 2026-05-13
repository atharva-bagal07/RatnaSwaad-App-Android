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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

// ── Brand tokens (same as HomeScreen) ─────────────────────────────────────────
private val DGreen = Color(0xFF245F35)
private val DAmber = Color(0xFFFFBD25)
private val DParchment = Color(0xFFFCF9F1)
private val DCardWhite = Color(0xFFFCFBF6)
private val DInputBg = Color(0xFFF6F3EB)
private val DTextDark = Color(0xFF1C1C17)
private val DTextMuted = Color(0xFF7A7A6E)
private val DPoppinsBold = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold))
private val DPacifico = FontFamily(Font(R.font.pacifico_regular))

// ── Screen ─────────────────────────────────────────────────────────────────────
@Composable
fun ProductDetailScreen(
    product: MangoProduct,
    onBack: () -> Unit = {},
    onAddToCart: (MangoProduct, Int) -> Unit = { _, _ -> }
) {
    var quantity by remember { mutableIntStateOf(1) }
    val totalPrice = product.price * quantity

    Box(modifier = Modifier
        .fillMaxSize()
        .background(DParchment)) {

        // ── Scrollable content ─────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp) // space for sticky bottom bar
        ) {

            // ── Top Bar ────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DCardWhite)
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DGreen
                    )
                }
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontFamily = DPoppinsBold,
                    color = DGreen
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = DGreen
                    )
                }
            }

            // ── Product Image ──────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(DInputBg),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )

                // Best seller badge overlay
                if (product.isBestSeller) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .background(DAmber, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "⭐ BEST SELLER",
                            fontSize = 11.sp,
                            fontFamily = DPoppinsBold,
                            color = DGreen
                        )
                    }
                }

                // Origin badge overlay
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                        .background(DCardWhite.copy(alpha = 0.92f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "📍 Ratnagiri Estate",
                        fontSize = 11.sp,
                        fontFamily = DPoppinsBold,
                        color = DGreen
                    )
                }
            }

            // ── Name + Price ───────────────────────────────────────────
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = product.name,
                    fontSize = 26.sp,
                    fontFamily = DPoppinsBold,
                    color = DGreen
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹${product.price}",
                        fontSize = 22.sp,
                        fontFamily = DPoppinsBold,
                        color = if (product.isBestSeller) DAmber else DGreen
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "· ${product.pieces} pcs · incl. delivery",
                        fontSize = 12.sp,
                        fontFamily = DPoppinsBold,
                        color = DTextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Specs row ──────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecChip(label = "Variety", value = "Alphonso (Hapus)")
                SpecChip(label = "Origin", value = "Ratnagiri, MH")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecChip(label = "Packaging", value = "Eco Wood Straw")
                SpecChip(label = "Delivery", value = "2–3 days")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Trust badges ───────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TrustBadge(
                    emoji = "🚚",
                    label = "Delivered in\n24–48 hrs",
                    modifier = Modifier.weight(1f)
                )
                TrustBadge(
                    emoji = "💵",
                    label = "Cash on\nDelivery",
                    modifier = Modifier.weight(1f)
                )
                TrustBadge(
                    emoji = "🌿",
                    label = "100%\nChemical-free",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── About ──────────────────────────────────────────────────
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "About this box",
                    fontSize = 16.sp,
                    fontFamily = DPoppinsBold,
                    color = DGreen
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description + ". Each mango is hand-picked at peak maturity by our farmers in Ratnagiri. Ripened naturally — no carbide, no preservatives. From orchard to your doorstep in 2–3 days.",
                    fontSize = 13.sp,
                    fontFamily = DPoppinsBold,
                    color = DTextMuted,
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Quantity selector ──────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DCardWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "How many boxes?",
                        fontSize = 15.sp,
                        fontFamily = DPoppinsBold,
                        color = DGreen
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Minus button
                        QuantityButton(
                            label = "−",
                            onClick = { if (quantity > 1) quantity-- },
                            enabled = quantity > 1
                        )

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$quantity",
                                fontSize = 28.sp,
                                fontFamily = DPoppinsBold,
                                color = DGreen
                            )
                            Text(
                                text = if (quantity == 1) "box" else "boxes",
                                fontSize = 12.sp,
                                fontFamily = DPoppinsBold,
                                color = DTextMuted
                            )
                        }

                        // Plus button
                        QuantityButton(
                            label = "+",
                            onClick = { quantity++ },
                            enabled = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = DInputBg)
                    Spacer(modifier = Modifier.height(12.dp))

                    // Order summary
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${product.pieces} pcs × $quantity box${if (quantity > 1) "es" else ""}",
                            fontSize = 13.sp,
                            fontFamily = DPoppinsBold,
                            color = DTextMuted
                        )
                        Text(
                            text = "${product.pieces * quantity} mangoes",
                            fontSize = 13.sp,
                            fontFamily = DPoppinsBold,
                            color = DGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "₹${product.price} × $quantity",
                            fontSize = 13.sp,
                            fontFamily = DPoppinsBold,
                            color = DTextMuted
                        )
                        Text(
                            text = "₹$totalPrice",
                            fontSize = 18.sp,
                            fontFamily = DPoppinsBold,
                            color = DGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        // ── Sticky bottom bar ──────────────────────────────────────────
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(DCardWhite)
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Total",
                        fontSize = 12.sp,
                        fontFamily = DPoppinsBold,
                        color = DTextMuted
                    )
                    Text(
                        text = "₹$totalPrice",
                        fontSize = 22.sp,
                        fontFamily = DPoppinsBold,
                        color = DGreen
                    )
                }

                Button(
                    onClick = { onAddToCart(product, quantity) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (product.isBestSeller) DAmber else DGreen
                    ),
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = if (product.isBestSeller) DGreen else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add to Cart",
                        fontSize = 15.sp,
                        fontFamily = DPoppinsBold,
                        color = if (product.isBestSeller) DGreen else Color.White
                    )
                }
            }
        }
    }
}

// ── Reusable components ────────────────────────────────────────────────────────

@Composable
private fun SpecChip(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(DInputBg, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = label.uppercase(),
            fontSize = 9.sp,
            fontFamily = DPoppinsBold,
            color = DTextMuted,
            letterSpacing = 1.sp
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontFamily = DPoppinsBold,
            color = DGreen
        )
    }
}

@Composable
private fun TrustBadge(emoji: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = DInputBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = emoji, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                fontFamily = DPoppinsBold,
                color = DTextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun QuantityButton(label: String, onClick: () -> Unit, enabled: Boolean) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(
                color = if (enabled) DGreen else DInputBg,
                shape = CircleShape
            )
            .then(
                if (!enabled) Modifier.border(1.dp, DInputBg, CircleShape) else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick, enabled = enabled) {
            Text(
                text = label,
                fontSize = 22.sp,
                fontFamily = DPoppinsBold,
                color = if (enabled) Color.White else DTextMuted
            )
        }
    }
}