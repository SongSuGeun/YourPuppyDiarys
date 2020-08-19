package com.example.your_puppy_diary.main.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_puppy_diary.R

class HomeAdapter(
    val context: Context,
    private val dogImageList: MutableList<String>?
) : RecyclerView.Adapter<HomeAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.dog_image_item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dogImageList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeAdapter.Holder, position: Int) {
        if (!dogImageList.isNullOrEmpty()) holder.bind(dogImageList)
        else holder.bind(null)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dogImageList: MutableList<String>?) {
            dogImageList?.let {
                // TODO DataをBindすること。
            }
        }
    }
}