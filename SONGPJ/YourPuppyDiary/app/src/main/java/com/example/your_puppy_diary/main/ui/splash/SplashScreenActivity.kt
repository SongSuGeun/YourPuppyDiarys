package com.example.your_puppy_diary.main.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.your_puppy_diary.main.MainTopActivity

class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainTopActivity::class.java)
        startActivity(intent)
        finish()
    }
}