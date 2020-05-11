@file:Suppress("UNUSED_VARIABLE", "MemberVisibilityCanBePrivate")

package com.afsar.ekhidki.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.afsar.ekhidki.BuildConfig
import com.afsar.ekhidki.DetailsPage
import com.afsar.ekhidki.Models.Category
import com.afsar.ekhidki.MyCart
import com.afsar.ekhidki.R
import com.afsar.ekhidki.ViewModel.VModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prod_ver_item.view.*

@Suppress("unused")
class HomeFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView1: RecyclerView
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var myPager: MyPager
    lateinit var sadapter: CustomAdapter
    lateinit var sadapter1: CustomAdapter1
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var vModel: VModel

    private lateinit var category: ArrayList<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("oncreateView", "called")
        rootView = inflater.inflate(R.layout.home_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("onActivityCreated", "called")
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    private fun initView() {
        Log.d("initView", "called")
        vModel = ViewModelProviders.of(this).get(VModel::class.java)
        var scrollView: NestedScrollView = rootView.findViewById(R.id.home_body)
        recyclerView = rootView.findViewById(R.id.my_recycler_view)
        recyclerView1 = rootView.findViewById(R.id.products_recycler_view)
        tabLayout = rootView.findViewById(R.id.tabDots)
        viewPager = rootView.findViewById(R.id.pager)
        tabLayout.setupWithViewPager(viewPager, false)
        myPager = MyPager(activity)
        viewPager.adapter = myPager

        category = ArrayList()
        category.add(Category("VadaPav", "Famous Snack"))
        category.add(Category("SamosaPav", "Famous Snack"))
        category.add(Category("BhajiPav", "Famous Snack"))
        category.add(Category("ManchurianPav", "Famous Snack"))

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
            sadapter = CustomAdapter(category)
            recyclerView.adapter = sadapter
            sadapter.notifyDataSetChanged()
        }

        recyclerView1.apply {
            val layoutManager1 = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView1.layoutManager = layoutManager1
            sadapter1 = CustomAdapter1(category)
            recyclerView1.adapter = sadapter1
            sadapter1.notifyDataSetChanged()
        }
    }

    class MyPager(private val context: Context?) : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return `object` == view
        }

        override fun getCount(): Int {
            return 4
        }

        @SuppressLint("InflateParams")
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view =
                LayoutInflater.from(context).inflate(R.layout.pager_item, null)
            val imageView =
                view.findViewById<ImageView>(R.id.image)
            Picasso.get().load(getImageAt(position)).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

        private fun getImageAt(position: Int): String? {
            Log.d("myPager", "called")
            return when (position) {
                0 -> {
                    val path0 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner1)
                    path0.toString()
                }
                1 -> {
                    val path =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner1)
                    path.toString()
                }
                2 -> {
                    val path1 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner1)
                    path1.toString()
                }
                3 -> {
                    val path2 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner1)
                    path2.toString()
                }
                else -> thumbnail
            }
        }
    }


    class CustomAdapter(private val sList: ArrayList<Category>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.series_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
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


    class CustomAdapter1(private val sList: ArrayList<Category>) :
        RecyclerView.Adapter<CustomAdapter1.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.prod_ver_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            holder.itemView.addtoCart.setOnClickListener {
                addToCart(sList[position])
                Toast.makeText(context, "Add To Cart", Toast.LENGTH_LONG).show()
            }
            holder.itemView.body.setOnClickListener {
                context.startActivity(Intent(context, DetailsPage::class.java))
            }
            holder.bindItems(sList[position])
        }

        override fun getItemCount(): Int {
            return sList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(sdata: Category) {
                Log.d("onActivityCreated", "called")
                val body = itemView.findViewById<CardView>(R.id.body)
                val name = itemView.findViewById<TextView>(R.id.name)
                val desc = itemView.findViewById<TextView>(R.id.description)
                val cart = itemView.findViewById<ImageView>(R.id.addtoCart)
                name.text = sdata.name
            }
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        var TAG = HomeFragment::class.java.simpleName
        var thumbnail: String = "https://photos.google.com/?tab=iq&authuser=0&pageId=none"
        fun addToCart(sdata: Category) {
            MyCart.sdata = sdata
        }
    }
}