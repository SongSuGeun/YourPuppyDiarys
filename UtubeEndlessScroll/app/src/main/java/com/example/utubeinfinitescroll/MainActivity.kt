package com.example.utubeinfinitescroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val numberList: MutableList<String> = ArrayList()
    var page = 1
    var isLoading = false
    val limit = 10

    lateinit var adapter: NumberAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerViewTest: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        recyclerViewTest = findViewById(R.id.recyclerView)
        recyclerViewTest.layoutManager = layoutManager
        getPage()

        recyclerViewTest.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // 마지막으로 스코롤 내려가면 0
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        page++
                        getPage()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun getPage() {
        isLoading = true
        progressBar.visibility = VISIBLE
        val start = (page - 1) * limit
        val end = (page) * limit

        for (i in start..end) {
            numberList.add("item $i")
        }

        Handler().postDelayed({
            if (::adapter.isInitialized) {
                adapter.notifyDataSetChanged()
            } else {
                adapter = NumberAdapter(this)
                recyclerViewTest.adapter = adapter
            }
            isLoading = false
            progressBar.visibility = GONE
        }, 5000)

    }

    class NumberAdapter(private val activity: MainActivity) :
        RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
            return NumberViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.rv_child_number,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return activity.numberList.size
        }

        override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
            holder.tvNumber.text = activity.numberList[position]
        }

        class NumberViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val tvNumber = v.findViewById<TextView>(R.id.tv_number)
        }
    }

}
