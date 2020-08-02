package com.example.your_puppy_diary.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.your_puppy_diary.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_top_act.*

class MainTopActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_top_act)

        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigation.setupWithNavController(navController)
    }
}
