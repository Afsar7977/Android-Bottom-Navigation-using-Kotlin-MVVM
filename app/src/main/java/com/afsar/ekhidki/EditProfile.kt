package com.afsar.ekhidki

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.lang.Exception

class EditProfile : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        try {
            val flag = intent.getStringExtra("flag")
            if (flag!! == "!log") {
                editprofile.text = "Register"
            }
            editprofile.setOnClickListener {
                registerUser(
                    firstname.text.toString(), lastname.text.toString(),
                    age.text.toString(), email.text.toString(), phone.text.toString()
                )
            }
        } catch (e: Exception) {
            editprofile.text = "Edit Profile"
            e.printStackTrace()
        }

    }

    private fun registerUser(s1: String, s2: String, s3: String, s4: String, s5: String) {
        Log.d("register", "$s1::$s2::$s3::$s4::$s5")
        val intent = Intent(this@EditProfile, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}