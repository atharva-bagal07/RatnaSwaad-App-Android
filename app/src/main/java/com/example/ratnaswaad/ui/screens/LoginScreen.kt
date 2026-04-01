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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratnaswaad.R

@Composable
fun LoginScreen(goToOtpScreen: () -> Unit) {

    var phoneNum by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var verificationId by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color(0xFFFCF9F1))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            contentAlignment = Alignment.Center
        )
        {
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
                        fontFamily = FontFamily(
                            Font(R.font.poppins_bold, FontWeight.SemiBold)
                        ),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false // Removes the extra padding
                            )
                        )
                    )
                    Text(
                        text = "Grown with care, shared with love.",
                        color = Color(0xFF714B26),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pacifico_regular)),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false // Removes the extra padding
                            )
                        )
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f), contentAlignment = Alignment.TopCenter
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
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

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
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        singleLine = true,
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                text = "Password",
                                color = Color.DarkGray.copy(alpha = 0.6f)
                            )
                        },
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
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            AuthManager.sendOtp(
                                phone = phoneNum,
                                activity = activity,
                                onCodeSent = {
                                    verificationId = it
                                    Toast.makeText(context, "OTP Sent", Toast.LENGTH_SHORT).show()
                                    goToOtpScreen()

                                },
                                onError = {
                                    Toast.makeText(
                                        context,
                                        "Retry again in 20 seconds",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFBD25)),
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .height(50.dp)
                            .fillMaxWidth(0.8f)
                            .padding(bottom = 4.dp),
                        shape = RoundedCornerShape(30)
                    ) {
                        Text(
                            "Login", fontFamily = FontFamily(
                                Font(R.font.poppins_bold, FontWeight.SemiBold)
                        ), fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    Text(
                        text = "Don't have an account? Sign up",
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_bold, FontWeight.Light)
                        )
                    )

                }
            }
        }
    }
}