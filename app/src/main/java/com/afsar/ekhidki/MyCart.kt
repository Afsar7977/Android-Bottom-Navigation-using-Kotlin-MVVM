package com.afsar.ekhidki

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afsar.ekhidki.Models.Category
import com.afsar.ekhidki.ViewModel.VModel
import kotlinx.android.synthetic.main.activity_my_cart.*


class MyCart : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var sadapter1: CustomAdapter1
    lateinit var layoutManager: GridLayoutManager
    lateinit var vModel: VModel
    private var categoryList: ArrayList<Category> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.cart_recycler_view)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        try {
            list.add(sdata)
            vModel = ViewModelProviders.of(this).get(VModel::class.java)
            vModel.getCartData(list).observe(this, Observer { data ->
                categoryList.addAll(data)
                sadapter1 = CustomAdapter1(categoryList)
                recyclerView.adapter = sadapter1
                sadapter1.notifyDataSetChanged()
            })
        } catch (e: Exception) {
            empty_cart.visibility = View.VISIBLE
            Toast.makeText(this@MyCart, "Oops Your Cart is Empty", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    class CustomAdapter1(private val sList: ArrayList<Category>) :
        RecyclerView.Adapter<CustomAdapter1.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.series_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(sList[position])
        }

        override fun getItemCount(): Int {
            return sList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(sdata: Category) {
                Log.d("onActivityCreated", "called")
                val name = itemView.findViewById<TextView>(R.id.name)
                name.text = sdata.name
            }
        }
    }


    companion object {
        lateinit var sdata: Category
        var list: ArrayList<Category> = ArrayList()
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