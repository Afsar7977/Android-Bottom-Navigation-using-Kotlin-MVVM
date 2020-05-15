@file:Suppress("UNUSED_VARIABLE")

package com.afsar.ekhidki

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_page.*

class DetailsPage : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        var scrollView = findViewById<ScrollView>(R.id.scrollView)
        val imagedesc = findViewById<ImageView>(R.id.imagedesc)

        val plusbtn = findViewById<Button>(R.id.add)
        val minusbtn = findViewById<Button>(R.id.minus)
        var addtoCart = findViewById<Button>(R.id.footer_cart)

        val descriptionTitle = findViewById<TextView>(R.id.desc_title)
        val descriptionText = findViewById<TextView>(R.id.desc_text)
        var disclaimer = findViewById<TextView>(R.id.dump_text)
        var deliverable = findViewById<TextView>(R.id.card1_title)
        var parcelable = findViewById<TextView>(R.id.card2_title)
        val cartnoTxt = findViewById<TextView>(R.id.cartno_text)
        val price = findViewById<TextView>(R.id.price)

        var cardView1 = findViewById<CardView>(R.id.card1)
        var cardView2 = findViewById<CardView>(R.id.card2)

        Picasso
            .get()
            .load(intent.getStringExtra("url"))
            .placeholder(R.drawable.trans_vada)
            .into(imagedesc)
        descriptionTitle.text = intent.getStringExtra("name")
        descriptionText.text = intent.getStringExtra("details")

        plusbtn.setOnClickListener {
            addInteger(cartnoTxt.text.toString())
        }

        minusbtn.setOnClickListener {
            minusInteger(cartnoTxt.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addInteger(addNo: String) {
        try {
            var i: Int = addNo.toInt()
            if (i >= 1) {
                i++
                val j: Int = i
                cartno_text.text = j.toString()
                val priceTxt = 30.times(j)
                Log.d("priceTxt", priceTxt.toString())
                price.text = "₹$priceTxt only"
            }
        } catch (e: Exception) {
            Log.d("error", "occurred")
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun minusInteger(minusNo: String) {
        try {
            var i: Int = minusNo.toInt()
            if (i != 0 && i > 1) {
                i--
                val j: Int = i
                cartno_text.text = j.toString()
                val priceTxt = 30.times(j)
                Log.d("priceTxt", priceTxt.toString())
                price.text = "₹$priceTxt only"
            }
        } catch (e: Exception) {
            Log.d("error", "occurred")
            e.printStackTrace()
        }
    }
}