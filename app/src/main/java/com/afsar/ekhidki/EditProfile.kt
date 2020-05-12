package com.afsar.ekhidki

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.afsar.ekhidki.Mail.AppExecutors
import com.afsar.ekhidki.Models.User
import com.afsar.ekhidki.Room.AppDb
import kotlinx.android.synthetic.main.activity_edit_profile.*

@Suppress("DEPRECATION")
class EditProfile : AppCompatActivity() {

    private lateinit var appDb: AppDb
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private val PREFS = "eKhidki"

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        appDb = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "User"
        ).fallbackToDestructiveMigration().build()

        sharedPreferences = getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        male.setOnClickListener {
            male.background = getDrawable(R.drawable.background)
            male.setTextColor(resources.getColor(R.color.white))
            female.background = getDrawable(R.drawable.unselectedbackground)
            female.setTextColor(resources.getColor(R.color.colorAccent))
            user_gender = "male"
        }
        female.setOnClickListener {
            female.background = getDrawable(R.drawable.background)
            female.setTextColor(resources.getColor(R.color.white))
            male.background = getDrawable(R.drawable.unselectedbackground)
            male.setTextColor(resources.getColor(R.color.colorAccent))
            user_gender = "male"
        }

        try {
            val flag = intent.getStringExtra("flag")
            if (flag!! == "!log") {
                editprofile.text = "Register"
            }
            editprofile.setOnClickListener {
                registerUser(
                    firstname.text.toString(),
                    lastname.text.toString(),
                    age.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    user_gender
                )
            }
        } catch (e: Exception) {
            editprofile.text = "Edit Profile"
            e.printStackTrace()
        }

    }

    private fun registerUser(
        s1: String,
        s2: String,
        s3: String,
        s4: String,
        s5: String,
        s6: String
    ) {
        Log.d("register", "$s1::$s2::$s3::$s4::$s5::$s6")
        val appException = AppExecutors()
        appException.diskIO().execute {
            try {
                val insert = appDb.userDao().insertUser(User(s1, s2, s3, s4, s5, s6))
                Log.d("insert", insert.toString())
                appException.mainThread().execute {
                    editor.putString("token", "logged").apply()
                    val intent = Intent(this@EditProfile, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                appException.mainThread().execute {
                    Toast.makeText(
                        applicationContext,
                        "Some Error Occured Please Try Again",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    startActivity(Intent(this, Login::class.java))
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        var user_gender: String = ""
    }
}