package com.example.mymemoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mymemoapp.data.ListViewModel
import com.example.mymemoapp.data.MemoData

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity() {

    var viewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentLayout, MemoListFragment())
        fragmentTransaction.commit()

        viewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(ListViewModel::class.java)

        fab.setOnClickListener { view ->
//            val intent = Intent(applicationContext, DetailActivity::class.java)
//            startActivity(intent)
            viewModel!!.let {
                val memoData = MemoData()
                memoData.title = "제목 테스트"
                memoData.summary = "요약 내용 테스트"
                memoData.createdAt = Date()
                it.addMemo(memoData)
            }
        }
    }
}
