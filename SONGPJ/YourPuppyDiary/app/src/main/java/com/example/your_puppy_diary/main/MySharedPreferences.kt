package com.example.your_puppy_diary.main

import android.content.Context
import android.content.SharedPreferences
import com.example.your_puppy_diary.main.data.CalendarModel
import com.google.gson.Gson

class MySharedPreferences(context: Context) {
    companion object {
        private const val CALENDAR_DATE = "calendarDate"
        private const val CALENDAR_MODEL = "calendarModel"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(CALENDAR_DATE, 0)

    var myCalendar: CalendarModel
        set(calendarModel) {
            val setGson: String = Gson().toJson(myCalendar)
            preferences.edit()
                .putString(CALENDAR_MODEL.createDateKey(calendarModel), setGson)
                .apply()
        }
        get() {
            val getGson = Gson()
            val calendarModel = preferences.getString(CALENDAR_MODEL, "")
            return getGson.fromJson(calendarModel, CalendarModel::class.java)
        }
}