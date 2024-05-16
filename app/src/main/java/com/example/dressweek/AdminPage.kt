package com.example.dressweek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AdminPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.greyBlue)
        //for Admin
        val name = "Admin"
        val pword = "admin"

        val signinBtn = findViewById<Button>(R.id.signin_btn)
        signinBtn.setOnClickListener {
            val emailInput = findViewById<EditText>(R.id.email_input).text.toString()
            val passwordInput = findViewById<EditText>(R.id.password_input).text.toString()
            if (emailInput == name && passwordInput == pword) {
                val intent1 = Intent(this, HomeActivity::class.java)
                startActivity(intent1)
            } else {
                Toast.makeText(this, "Username or Password incorrect", Toast.LENGTH_SHORT).show()
            }
        }
        var signup_btn = findViewById<TextView>(R.id.gotoRegister)
        signup_btn.setOnClickListener(){
            val intent2 = Intent(this,SignUpActivity::class.java)
            startActivity(intent2)
        }
    }
}