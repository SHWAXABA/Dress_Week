package com.example.dressweek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        var toSignin = findViewById<TextView>(R.id.gotoLogin)
        toSignin.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        var signUp = findViewById<Button>(R.id.signup_btn)
        signUp.setOnClickListener(){
            var username = findViewById<EditText>(R.id.username_input).text.toString()
            var email = findViewById<EditText>(R.id.email_input).text.toString()
            var password = findViewById<EditText>(R.id.password_input).text.toString()
            var confirmpassword = findViewById<EditText>(R.id.confirmpassword_input).text.toString()
            if(password==confirmpassword){
                var intent2 = Intent(this,SignUpThankYou::class.java)
                startActivity(intent2)
            }else{
                Toast.makeText(this,"Password Incorrect",Toast.LENGTH_SHORT).show()
            }

        }
    }
}