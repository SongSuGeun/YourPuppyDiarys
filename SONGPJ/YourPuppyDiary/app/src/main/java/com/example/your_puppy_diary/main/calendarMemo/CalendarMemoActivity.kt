package com.example.your_puppy_diary.main.calendarMemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerAppCompatActivity

class CalendarMemoActivity : DaggerAppCompatActivity() {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        fun getIntent(context: Context, calendarMemo: CalendarModel) =
            Intent(context, CalendarMemoActivity::class.java).apply {
                this.putExtra(CALENDAR_MEMO, calendarMemo)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.calendar_act)
    }

}