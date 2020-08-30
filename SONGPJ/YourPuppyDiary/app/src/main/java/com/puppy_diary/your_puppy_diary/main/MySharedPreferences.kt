package com.puppy_diary.your_puppy_diary.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.puppy_diary.your_puppy_diary.main.data.CalendarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPreferences(context: Context) {
    companion object {
        private const val CALENDAR_DATE = "calendarDate"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(CALENDAR_DATE, 0)

    fun editSharedPreference(calendarModel: CalendarModel) {
        val editDate = createDateKey(calendarModel)
        val getCalendarMemo = getSharedPreference(editDate)
        val setGson = if (getCalendarMemo.isNullOrEmpty()) {
            Gson().toJson(mutableListOf(calendarModel))
        } else {
            getCalendarMemo.add(calendarModel)
            Gson().toJson(getCalendarMemo)
        }
        preferences.edit()
            .putString(editDate, setGson)
            .apply()
    }

    fun getSharedPreference(showDate: String): MutableList<CalendarModel> {
        val getGson = Gson()
        val json = preferences.getString(showDate, "")
        return if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<List<CalendarModel>?>() {}.type
            getGson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun removeSharedPreference(date: String, position: Int) {
        val currentCalendarModel = getSharedPreference(date)
        val editDate = createDateKey(currentCalendarModel.first())
        preferences.edit()
            .remove(editDate)
            .apply()
        if (!currentCalendarModel.isNullOrEmpty()) {
            currentCalendarModel.removeAt(position)
            val setGson = Gson().toJson(currentCalendarModel)
            preferences.edit()
                .putString(editDate, setGson)
                .apply()
        }
    }
}