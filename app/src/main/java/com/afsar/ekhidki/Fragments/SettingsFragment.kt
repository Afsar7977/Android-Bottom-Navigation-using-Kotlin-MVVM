@file:Suppress("LocalVariableName", "PackageName")

package com.afsar.ekhidki.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.afsar.ekhidki.EditProfile
import com.afsar.ekhidki.Models.Utils
import com.afsar.ekhidki.R
import com.afsar.ekhidki.Room.AppDb
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var appDb: AppDb
    private var utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.settings_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        appDb = Room
            .databaseBuilder(rootView.context, AppDb::class.java, "User")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        item1.setOnClickListener {
            startActivity(Intent(activity, EditProfile::class.java))
        }

        val user_name = utils.getUser(appDb).fname + " " + utils.getUser(appDb).lname
        name.text = user_name

        item2.setOnClickListener {
            Toast.makeText(rootView.context, "Coming Soon!!", Toast.LENGTH_LONG).show()
        }

        item3.setOnClickListener {
            val intent = Intent(rootView.context, EditProfile::class.java)
            intent.putExtra("flag", "logout")
            rootView.context.startActivity(intent)
        }
    }
}