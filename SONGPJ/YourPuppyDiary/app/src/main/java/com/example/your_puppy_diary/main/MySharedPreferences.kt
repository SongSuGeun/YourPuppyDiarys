package com.example.your_puppy_diary.main

import android.content.Context
import android.content.SharedPreferences
import com.example.your_puppy_diary.main.data.CalendarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPreferences(context: Context) {
    companion object {
        private const val CALENDAR_DATE = "calendarDate"
        private const val CALENDAR_MODEL = "calendarModel"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(CALENDAR_DATE, 0)

    fun editSharedPreference(calendarModel: CalendarModel) {
        val editDate = CALENDAR_MODEL.createDateKey(calendarModel)
        val getCalendarMemo = getSharedPreference(editDate)
        if (getCalendarMemo.isNullOrEmpty()) {
            val setGson = Gson().toJson(mutableListOf(calendarModel))
            preferences.edit()
                .putString(editDate, setGson)
                .apply()
        } else {
            getCalendarMemo.add(calendarModel)
            val setGson = Gson().toJson(getCalendarMemo)
            preferences.edit()
                .putString(editDate, setGson)
                .apply()
        }
    }

    fun getSharedPreference(showDate: String): MutableList<CalendarModel>? {
        val getGson = Gson()
        val json = preferences.getString(showDate, "")
        return if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<List<CalendarModel>?>() {}.type
            getGson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }
}