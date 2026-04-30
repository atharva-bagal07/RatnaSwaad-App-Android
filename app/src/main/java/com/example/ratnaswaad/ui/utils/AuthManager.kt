package com.example.ratnaswaad.ui.utils

import android.app.Activity
import android.util.Log
import com.example.ratnaswaad.data.FirebaseRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object AuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun sendOtp(
        phone: String,
        activity: Activity,
        onCodeSent: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val cleanPhone = phone.trim().replace(" ", "")
        val formattedPhone = if (cleanPhone.startsWith("+91")) cleanPhone else "+91$cleanPhone"

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(formattedPhone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto-retrieval or instant verification (happens on some devices/test numbers)
                    auth.signInWithCredential(credential)
                        .addOnSuccessListener {
                            Log.d("OTP", "Auto verification success")
                            // Note: navigation is handled by onCodeSent + OtpScreen flow
                            // Auto-verify won't call onCodeSent, so handle separately if needed
                        }
                        .addOnFailureListener {
                            Log.e("OTP", "Auto verification failed: ${it.message}")
                        }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("OTP", "Verification Failed: ${e.message}")
                    onError(e.message ?: "Verification failed. Please try again.")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("OTP", "Code sent, verificationId: $verificationId")
                    onCodeSent(verificationId)
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(
        verificationId: String,
        code: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        goToHomeScreen: () -> Unit   // kept for compatibility, calls onSuccess then this
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    val uid = user?.uid ?: run {
                        onError("Could not retrieve user. Please try again.")
                        return@addOnCompleteListener
                    }
                    val phone = user.phoneNumber

                    FirebaseRepository.saveUserIfNew(
                        uid = uid,
                        phone = phone,
                        onComplete = {
                            onSuccess()
                            goToHomeScreen()
                        },
                        onError = { error ->
                            onError(error)
                        }
                    )
                } else {
                    val errorMsg = task.exception?.message ?: "Invalid OTP. Please try again."
                    Log.e("OTP", "Sign-in failed: $errorMsg")
                    onError(errorMsg)
                }
            }
    }
}
