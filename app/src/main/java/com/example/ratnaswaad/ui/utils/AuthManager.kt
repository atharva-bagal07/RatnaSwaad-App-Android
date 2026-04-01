import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

object AuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun sendOtp(
        phone: String,
        activity: Activity,
        onCodeSent: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        // ✅ Clean + format phone
        val cleanPhone = phone.replace(" ", "")
        val formattedPhone = if (cleanPhone.startsWith("+91")) {
            cleanPhone
        } else {
            "+91$cleanPhone"
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(formattedPhone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d("OTP", "Auto verification success")
                            }
                        }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("OTP", "Verification Failed: ${e.message}")
                    onError(e.message ?: "Error")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("OTP", "Code Sent")
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
        onError: (String) -> Unit
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("OTP", "Verification Success")
                    onSuccess()
                } else {
                    Log.e("OTP", "Verification Failed: ${task.exception?.message}")
                    onError(task.exception?.message ?: "Invalid OTP")
                }
            }
    }
}