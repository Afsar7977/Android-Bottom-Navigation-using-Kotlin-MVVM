package com.afsar.ekhidki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Otp : AppCompatActivity() {

    private lateinit var otp: EditText
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        otp = findViewById(R.id.otp)
        button1 = findViewById(R.id.verify)
        val getOtpString: String = intent.getStringExtra("otpNumber")!!
        Log.d("otpString", getOtpString)
        button1.setOnClickListener {
            startActivity(Intent(this@Otp, MainActivity::class.java))
        }
    }
}
