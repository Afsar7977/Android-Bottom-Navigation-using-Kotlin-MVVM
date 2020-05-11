package com.afsar.ekhidki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var button1: Button
    private lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        findViewById<TextInputLayout>(R.id.text_input_layout)
        email = findViewById(R.id.phone)
        password = findViewById(R.id.password)
        button1 = findViewById(R.id.signin)

        button1.setOnClickListener {
            //TODO something
            login(email.text.toString(), password.text.toString())
        }
    }

    private fun login(s1: String, s2: String) {
        Log.d("login", "$s1::$s2")
        val intent = Intent(this@Login, EditProfile::class.java)
        intent.putExtra("flag", "!log")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}