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


@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    goToHomeScreen: () -> Unit
) {

    var otp by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf("") }

    val context = LocalContext.current
    val otpState = rememberTextFieldState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Enter OTP Code")
        BasicTextField(
            state = otpState,
            inputTransformation = InputTransformation.maxLength(6),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
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
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "Resend Code"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                AuthManager.verifyOtp(
                    verificationId = verificationId,
                    code = otp,
                    onSuccess = {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        goToHomeScreen()
                    },
                    onError = {
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
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
        )
        {
            Text(
                "Verify OTP", fontFamily = FontFamily(
                    Font(R.font.poppins_bold, FontWeight.SemiBold)
                ), fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun Digit(
    char: Char,
    highlight: Boolean = false
) {
    val borderSize by animateDpAsState(
        targetValue = if (highlight) 2.dp else 1.dp
    )
    val borderColor by animateColorAsState(
        targetValue = if (highlight) Color.Blue else Color.LightGray
    )
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(borderSize, borderColor, RoundedCornerShape(4.dp))
            .background(Color.Yellow, RoundedCornerShape(4.dp))
    ) {
        Text(
            text = char.toString(),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}