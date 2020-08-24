package com.example.your_puppy_diary.main

import com.example.your_puppy_diary.main.data.CalendarModel
import java.time.LocalDate

fun createDateKey(calendarModel: CalendarModel): String {
    return calendarModel.let {
        "${it.year}${it.month}${it.day}"
    }
}

fun createInitDateKey(localDate: LocalDate): String {
    return localDate.let {
        "${it.year}${it.monthValue}${it.dayOfMonth}"
    }
}