package com.afsar.ekhidki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.afsar.ekhidki.Mail.AppExecutors
import com.afsar.ekhidki.Room.AppDb
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var button1: Button
    private lateinit var password: EditText
    private lateinit var appDb: AppDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appDb = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "User"
        ).fallbackToDestructiveMigration().build()

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
        val appExecutors = AppExecutors()
        appExecutors.diskIO().execute {
            try {
                val flag = appDb.userDao().findByName(s1, s2)
                Log.d("flag", flag.toString())
            } catch (e: Exception) {
                appExecutors.mainThread().execute {
                    val intent = Intent(this@Login, EditProfile::class.java)
                    intent.putExtra("flag", "!log")
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                e.printStackTrace()
            }

        }

    }
}