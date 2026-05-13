package com.example.ratnaswaad.ui.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratnaswaad.R
import com.example.ratnaswaad.ui.utils.AuthManager

@Composable
fun OtpScreen(
    verificationId: String,        // ✅ received from Navigation
    goToHomeScreen: () -> Unit
) {
    val context = LocalContext.current
    val otpState = rememberTextFieldState()
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCF9F1))
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enter OTP",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold)),
            color = Color(0xFF245F35)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We sent a 6-digit code to your phone",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OTP input — hidden field drives the 6 visible boxes
        BasicTextField(
            state = otpState,
            inputTransformation = InputTransformation.maxLength(6),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            lineLimits = TextFieldLineLimits.SingleLine,
            decorator = {
                val otpCode = otpState.text.toString()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(6) { index ->
                        Digit(
                            char = otpCode.getOrElse(index) { ' ' },
                            highlight = index == otpState.text.length
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                val enteredOtp = otpState.text.toString()   // ✅ read from otpState
                // Debug bypass
                if (verificationId == "debug_bypass") {
                    goToHomeScreen()
                    return@Button
                }
                if (enteredOtp.length < 6) {
                    Toast.makeText(context, "Please enter the full 6-digit OTP", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }
                isLoading = true
                AuthManager.verifyOtp(
                    verificationId = verificationId,        // ✅ actual verificationId
                    code = enteredOtp,
                    onSuccess = {
                        isLoading = false
                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                        goToHomeScreen()
                    },
                    onError = { error ->
                        isLoading = false
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    },
                    goToHomeScreen = goToHomeScreen
                )
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
                    text = "Verify OTP",
                    fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold)),
                    fontSize = 16.sp,
                    color = Color(0xFF245F35)
                )
            }
        }
    }
}

@Composable
private fun Digit(
    char: Char,
    highlight: Boolean = false
) {
    val borderSize by animateDpAsState(targetValue = if (highlight) 2.dp else 1.dp)
    val borderColor by animateColorAsState(
        targetValue = if (highlight) Color(0xFF245F35) else Color.LightGray
    )
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(borderSize, borderColor, RoundedCornerShape(8.dp))
            .background(color = Color(0xFFF6F3EB), RoundedCornerShape(8.dp))
    ) {
        Text(
            text = if (char == ' ') "" else char.toString(),
            fontSize = 22.sp,
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.SemiBold))
        )
    }
}
