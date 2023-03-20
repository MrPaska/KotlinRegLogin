package com.example.register_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn = findViewById<Button>(R.id.button_login)
        auth = FirebaseAuth.getInstance()

        btn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_login)
            val pass = findViewById<EditText>(R.id.password_login)

            val email_ = email.text.toString()
            val pass_ = pass.text.toString()

            if (email_.isEmpty() || pass_.isEmpty()) {
                Toast.makeText(applicationContext, "Laukai turi buti uzpildyti", Toast.LENGTH_SHORT)
                    .show()
            } else {

                auth.signInWithEmailAndPassword(
                    email_, pass_
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(
                            applicationContext,
                            "Prisijungta :)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
                }
            }
        }
    }
}