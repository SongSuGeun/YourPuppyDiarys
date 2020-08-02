package com.example.your_puppy_diary.main.calendarMemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment

class CalendarMemoFragment : DaggerFragment() {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        fun newInstance(calendarMemo: CalendarModel) = CalendarMemoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CALENDAR_MEMO, calendarMemo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calendar_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}