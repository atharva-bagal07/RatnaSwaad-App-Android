package com.example.ratnaswaad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ratnaswaad.navigation.Navigation
import com.example.ratnaswaad.navigation.Routes
import com.example.ratnaswaad.ui.OtpCodeInput
import com.example.ratnaswaad.ui.screens.OtpScreen
import com.example.ratnaswaad.ui.theme.RatnaSwaadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RatnaSwaadTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OtpCodeInput()
                }
            }
        }
    }
}

