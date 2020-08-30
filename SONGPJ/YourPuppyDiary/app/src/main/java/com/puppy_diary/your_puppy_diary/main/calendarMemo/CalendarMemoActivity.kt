package com.puppy_diary.your_puppy_diary.main.calendarMemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.puppy_diary.your_puppy_diary.R
import com.puppy_diary.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerAppCompatActivity

class CalendarMemoActivity : DaggerAppCompatActivity() {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        fun getIntent(context: Context, calendarMemo: CalendarModel) =
            Intent(context, CalendarMemoActivity::class.java).apply {
                this.putExtra(CALENDAR_MEMO, calendarMemo)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_act)

        val calendarMemo = intent.getParcelableExtra<CalendarModel>(CALENDAR_MEMO)

        val fragment = CalendarMemoFragment.newInstance(calendarMemo)
        supportFragmentManager.beginTransaction()
            .replace(R.id.calendar_memo_fragment, fragment)
            .commit()
    }
}