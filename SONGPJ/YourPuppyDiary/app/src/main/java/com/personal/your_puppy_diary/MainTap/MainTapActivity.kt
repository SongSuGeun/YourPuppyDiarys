package com.personal.your_puppy_diary.MainTap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.your_puppy_diary.R

import kotlinx.android.synthetic.main.activity_main_tap.*

class MainTapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tap)
        setSupportActionBar(toolbar)
    }
}
