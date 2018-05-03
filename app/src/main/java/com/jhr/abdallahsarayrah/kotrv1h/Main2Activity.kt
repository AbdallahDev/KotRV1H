package com.jhr.abdallahsarayrah.kotrv1h

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//bellow i fill the spinner with categories
        val categoriesList = ArrayList<String>()
        categoriesList.add("choose category")
        categoriesList.add("laptop")
        categoriesList.add("smartphone")
        val categoriesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categoriesList)
        spinner.adapter = categoriesAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                if (spinner.selectedItem != "choose category") {
                    val url = "http://169.254.129.65/android/salesh/items_get.php?itemCategory=" +
                            "${spinner.selectedItem}"
                    val vollyRequest = Volley.newRequestQueue(this@Main2Activity)
                    val arrR = JsonArrayRequest(Request.Method.GET, url, null,
                            Response.Listener { response ->
                                for (x in 0 until response.length()) {
                                    textView.text = textView.text.toString() + " - " + response.getJSONObject(x).getString("item_name") + " - "
                                }
                                Toast.makeText(this@Main2Activity,
                                        response.length().toString(), Toast.LENGTH_SHORT).show()

                            }, Response.ErrorListener { error ->
                        Toast.makeText(this@Main2Activity, error.message, Toast.LENGTH_SHORT).show()
                    })
                    vollyRequest.add(arrR)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        val dialog = ShowImage()
        dialog.show(fragmentManager, "example")
    }
}
