package com.example.register_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val logoff = findViewById<Button>(R.id.logoff)

        logoff.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            finish()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

}
