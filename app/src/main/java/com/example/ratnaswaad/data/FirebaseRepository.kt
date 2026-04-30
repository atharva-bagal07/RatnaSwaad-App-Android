package com.example.ratnaswaad.data

import com.google.firebase.database.FirebaseDatabase

object FirebaseRepository {
    private val dbRef = FirebaseDatabase.getInstance().getReference("users")

    fun saveUserIfNew(
        uid: String,
        phone: String?,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ) {
        dbRef.child(uid).get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.exists()) {
                    val userData = mapOf(
                        "phone" to phone,
                        "createdAt" to System.currentTimeMillis()
                    )
                    dbRef.child(uid).setValue(userData)
                        .addOnSuccessListener { onComplete() }
                        .addOnFailureListener { onError(it.message ?: "Error saving user") }
                } else {
                    onComplete() // existing user, just proceed
                }
            }
            .addOnFailureListener { onError(it.message ?: "Error fetching user") }
    }
}