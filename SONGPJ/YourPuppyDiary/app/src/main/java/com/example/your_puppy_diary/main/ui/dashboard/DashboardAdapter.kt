package com.example.your_puppy_diary.main.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.data.CalendarModel
import kotlinx.android.synthetic.main.calendar_memo_item_list.view.*
import kotlinx.android.synthetic.main.dashboard_frag.view.*

class DashboardAdapter(val context: Context, private val calendarModel: ArrayList<CalendarModel>) :
    RecyclerView.Adapter<DashboardAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.calendar_memo_item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return calendarModel.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        println(calendarModel)
        holder.bind(calendarModel[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(calendarModel: CalendarModel?) {
            calendarModel?.let {
                itemView.todayNotSchedule.visibility = GONE
                itemView.recyclerCalenderMemo.visibility = VISIBLE

                itemView.title.text = it.title
                itemView.content.text = it.content
            } ?: run {
                itemView.todayNotSchedule.visibility = VISIBLE
                itemView.recyclerCalenderMemo.visibility = GONE
            }
        }
    }
}