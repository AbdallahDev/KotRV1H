package com.jhr.abdallahsarayrah.kotrv1h

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bellow i fill the spinner with categories
        val categoriesList = ArrayList<String>()
        categoriesList.add("choose category")
        categoriesList.add("laptop")
        categoriesList.add("smartphone")
        val categoriesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categoriesList)
        spinner_category.adapter = categoriesAdapter

        val itemsList = ArrayList<ItemsModel>()

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (spinner_category.selectedItem != "choose category") {
                    val url = "http://169.254.129.65/android/salesh/items_get.php?itemCategory=" +
                            "${spinner_category.selectedItem}"
                    val vollyRequest = Volley.newRequestQueue(this@MainActivity)
                    val arrR = JsonArrayRequest(Request.Method.GET, url, null,
                            Response.Listener { response ->
                                if (itemsList.isNotEmpty()) itemsList.clear()
                                (0 until response.length()).mapTo(itemsList) {
                                    ItemsModel(
                                            response.getJSONObject(it).getString("item_name"),
                                            response.getJSONObject(it).getDouble("item_price"),
                                            "http://169.254.129.65/android/salesh/images/" +
                                                    response.getJSONObject(it)
                                                            .getString("item_image") + ".jpg",
                                            response.getJSONObject(it).getString("item_category")
                                    )
                                }
                                recyclerView_items.layoutManager = LinearLayoutManager(this@MainActivity)
                                recyclerView_items.adapter = ItemsController(this@MainActivity, itemsList)
                            }, Response.ErrorListener { error ->
                        Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                    })
                    vollyRequest.add(arrR)
                } else {
                    itemsList.clear()
                    recyclerView_items.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView_items.adapter = ItemsController(this@MainActivity, itemsList)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


    }
}
