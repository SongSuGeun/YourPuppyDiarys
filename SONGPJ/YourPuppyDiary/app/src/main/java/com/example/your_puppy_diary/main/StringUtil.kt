package com.example.your_puppy_diary.main

import com.example.your_puppy_diary.main.data.CalendarModel

fun String.createDateKey(calendarModel: CalendarModel): String {
    return calendarModel.let {
        (it.year.plus(it.month).plus(it.day)).toString()
    }
}