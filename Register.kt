package com.example.register_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btn = findViewById<Button>(R.id.button_reg)
        val btn_log = findViewById<Button>(R.id.button_log)

        auth = FirebaseAuth.getInstance()

        btn_log.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_reg)
            val pass = findViewById<EditText>(R.id.password_reg)

            val email_ = email.text.toString()
            val pass_ = pass.text.toString()

            if (email_.isEmpty() || pass_.isEmpty()) {
                Toast.makeText(applicationContext, "Laukai turi buti uzpildyti", Toast.LENGTH_SHORT)
                    .show()
            } else {

                auth.createUserWithEmailAndPassword(
                    email_, pass_
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(
                            applicationContext,
                            "Registracija sekminga :)",
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