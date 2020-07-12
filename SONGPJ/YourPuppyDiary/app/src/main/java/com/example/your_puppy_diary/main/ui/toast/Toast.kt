package com.example.your_puppy_diary.main.ui.toast

import android.content.Context
import android.widget.Toast
import com.example.your_puppy_diary.R

fun basicAlarmToast(context: Context, time: String?) {
    time?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    } ?: run {
        Toast.makeText(context, context.getString(R.string.setting_cancel), Toast.LENGTH_SHORT).show()
    }
}