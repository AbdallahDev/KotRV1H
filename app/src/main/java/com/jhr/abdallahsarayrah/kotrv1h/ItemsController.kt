package com.jhr.abdallahsarayrah.kotrv1h

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*


/**
 * Created by abdallah.sarayrah on 12/29/2017.
 */
class ItemsController(private var context: Context, private var list: ArrayList<ItemsModel>)
    : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder as ItemViewHolder).bind(
                list[position].itemName,
                list[position].itemPrice,
                list[position].itemImage)

        holder.backgroundColor(list[position].itemCategory)

        holder.itemView.imageView.setOnClickListener {
            val args = Bundle()
            args.putString("itemImage", list[position].itemImage)
            args.putString("itemCategory", list[position].itemCategory)

            val fr = (context as Activity).fragmentManager
            val msgDialog = ShowImage()
            msgDialog.arguments = args
            msgDialog.show(fr, "Dialog")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)

        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(itemName: String, itemPrice: Double, itemImage: String) {
            itemView.textView_name.text = itemName
            itemView.textView_price.text = itemPrice.toString() + " JD"
            Picasso.with(itemView.context).load(itemImage).into(itemView.imageView)
        }

        fun backgroundColor(itemCategory: String) {
            if (itemCategory == "Laptop") itemView.setBackgroundColor(Color
                    .parseColor("#64B5F6"))
            if (itemCategory == "SmartPhone") itemView.setBackgroundColor(Color
                    .parseColor("#9575CD"))
        }

    }


    //---------------------------------------------------------------------------------------

    /*inner class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
        override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }*/

    //----------------------------------------------------------------------------------------------------


}