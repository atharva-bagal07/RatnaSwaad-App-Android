package com.example.ratnaswaad.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratnaswaad.R
import com.example.ratnaswaad.ui.utils.AuthManager

@Composable
fun SignUpScreen(
    goToLoginScreen: () -> Unit,
    goToOtpScreen: (String) -> Unit    // ✅ added so signup also leads to OTP
) {
    var name by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color(0xFFFCF9F1))
    ) {

        // ── Header / Logo ──────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ratna_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(68.dp)
                        .padding(end = 4.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "RatnaSwaad",
                        modifier = Modifier.padding(end = 12.dp),
                        color = Color(0xFF245F35),
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold)),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                    Text(
                        text = "Grown with care, shared with love.",
                        color = Color(0xFF714B26),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pacifico_regular)),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            }
        }

        // ── Signup Card ────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFBF6)),
                shape = RoundedCornerShape(8),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Create account",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold)),
                        color = Color(0xFF245F35)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Sign up with your phone number",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Name field
                    TextField(
                        singleLine = true,
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        placeholder = {
                            Text(
                                text = "Full Name",
                                color = Color.DarkGray.copy(alpha = 0.6f)
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF6F3EB),
                            unfocusedContainerColor = Color(0xFFF6F3EB),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Phone field
                    TextField(
                        singleLine = true,
                        value = phoneNum,
                        onValueChange = { phoneNum = it },
                        placeholder = {
                            Text(
                                text = "Phone Number",
                                color = Color.DarkGray.copy(alpha = 0.6f)
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF6F3EB),
                            unfocusedContainerColor = Color(0xFFF6F3EB),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // ✅ Sign Up now sends OTP just like Login
                    Button(
                        onClick = {
                            when {
                                name.isBlank() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter your name",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                phoneNum.isBlank() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter your phone number",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    isLoading = true
                                    AuthManager.sendOtp(
                                        phone = phoneNum,
                                        activity = activity,
                                        onCodeSent = { verificationId ->
                                            isLoading = false
                                            Toast.makeText(context, "OTP Sent!", Toast.LENGTH_SHORT)
                                                .show()
                                            goToOtpScreen(verificationId)
                                        },
                                        onError = { error ->
                                            isLoading = false
                                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                }
                            }
                        },
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFBD25)),
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(0.8f),
                        shape = RoundedCornerShape(30)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                color = Color(0xFF245F35),
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(22.dp)
                            )
                        } else {
                            Text(
                                text = "Sign Up",
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.poppins_bold,
                                        FontWeight.SemiBold
                                    )
                                ),
                                fontSize = 16.sp,
                                color = Color(0xFF245F35)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    TextButton(onClick = goToLoginScreen) {
                        Text(
                            text = "Already have an account? Login",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold))
                        )
                    }
                }
            }
        }
    }
}
