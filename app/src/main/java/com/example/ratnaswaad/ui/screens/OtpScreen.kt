package com.example.ratnaswaad.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp

@Composable
fun OtpScreen() {

    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf("") }

    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // 📱 Phone input
        TextField(
            value = phone,
            onValueChange = { phone = it },
            placeholder = { Text("Enter phone number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📲 Send OTP
        Button(
            onClick = {
                AuthManager.sendOtp(
                    phone = phone,
                    activity = activity,
                    onCodeSent = {
                        verificationId = it
                        Log.d("OTP", "Code Sent")
                    },
                    onError = {
                        Log.e("OTP", it)
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔐 OTP input
        TextField(
            value = otp,
            onValueChange = { otp = it },
            placeholder = { Text("Enter OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ Verify OTP
        Button(
            onClick = {
                AuthManager.verifyOtp(
                    verificationId = verificationId,
                    code = otp,
                    onSuccess = {
                        Log.d("OTP", "Login Success")
                    },
                    onError = {
                        Log.e("OTP", it)
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify OTP")
        }
    }
}
