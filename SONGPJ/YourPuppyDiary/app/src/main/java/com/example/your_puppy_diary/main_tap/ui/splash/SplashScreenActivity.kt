package com.example.your_puppy_diary.main_tap.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main_tap.MainTopActivity


class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainTopActivity::class.java)
        startActivity(intent)
        finish()
    }
}