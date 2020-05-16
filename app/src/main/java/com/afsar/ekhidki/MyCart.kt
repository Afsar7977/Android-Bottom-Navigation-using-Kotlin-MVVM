package com.afsar.ekhidki

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.afsar.ekhidki.Models.Products
import com.afsar.ekhidki.Room.AppDb1
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.prod_ver_item.view.*
import kotlinx.android.synthetic.main.prod_ver_item.view.body
import kotlinx.android.synthetic.main.series_item.view.*


class MyCart : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var sadapter1: CustomAdapter1
    lateinit var layoutManager: GridLayoutManager
    private lateinit var categoryList: ArrayList<Products>
    lateinit var appDb1: AppDb1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.cart_recycler_view)
        val layoutManager1 = LinearLayoutManager(this@MyCart, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager1

        try {
            categoryList = ArrayList()
            appDb1 = Room.databaseBuilder(applicationContext, AppDb1::class.java, "Products")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            categoryList.addAll(appDb1.productsDao().getProducts())
            sadapter1 = CustomAdapter1(categoryList)
            recyclerView.adapter = sadapter1
            sadapter1.notifyDataSetChanged()
        } catch (e: Exception) {
            empty_cart.visibility = View.VISIBLE
            Toast.makeText(this@MyCart, "Oops Your Cart is Empty", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        try {
            when {
                categoryList.isEmpty() -> {
                    empty_cart.visibility = View.VISIBLE
                }
                else -> {
                    empty_cart.visibility = View.GONE
                }
            }
        } catch (e: Exception) {
            Log.d("error", e.toString())
        }
    }

    class CustomAdapter1(private val sList: ArrayList<Products>) :
        RecyclerView.Adapter<CustomAdapter1.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.series_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            holder.itemView.cart_body.setOnClickListener {
                val intent = Intent(context, DetailsPage::class.java)
                intent.putExtra("name", sList[position].name)
                intent.putExtra("url", sList[position].image)
                intent.putExtra("details", sList[position].desc)
                intent.putExtra("price", sList[position].price)
                context.startActivity(intent)
            }
            holder.bindItems(sList[position])
        }

        override fun getItemCount(): Int {
            return sList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            @SuppressLint("SetTextI18n")
            fun bindItems(sdata: Products) {
                @Suppress("UNUSED_VARIABLE")
                val body = itemView.findViewById<CardView>(R.id.body)
                val name = itemView.findViewById<TextView>(R.id.name)
                val desc = itemView.findViewById<TextView>(R.id.description)
                val image = itemView.findViewById<ImageView>(R.id.img)
                val price = itemView.findViewById<TextView>(R.id.price)
                name.text = sdata.name
                desc.text = sdata.desc
                price.text = "₹${sdata.price} only"
                Picasso.get().load(sdata.image).placeholder(R.drawable.trans_vada).into(image)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}