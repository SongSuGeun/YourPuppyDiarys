package com.example.your_puppy_diary.main

import android.content.Context
import android.content.SharedPreferences
import com.example.your_puppy_diary.main.data.CalendarModel
import com.google.gson.Gson
import java.time.LocalDate

class MySharedPreferences(context: Context) {
    companion object {
        private const val CALENDAR_DATE = "calendarDate"
        private const val CALENDAR_MODEL = "calendarModel"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(CALENDAR_DATE, 0)

    fun editSharedPreference(calendarModel: CalendarModel) {
        val setGson: String = Gson().toJson(calendarModel)
        preferences.edit()
            .putString(CALENDAR_MODEL.createDateKey(calendarModel), setGson)
            .apply()
    }

    fun getSharedPreference(calendarModel: CalendarModel): ArrayList<CalendarModel>? {
        val getGson = Gson()
        val calendarModel = preferences.getString(CALENDAR_MODEL.createDateKey(calendarModel), "")
        println(calendarModel)
        return if (!calendarModel.isNullOrEmpty()) {
            println("song getSharedPreference")
            arrayListOf(getGson.fromJson(calendarModel, CalendarModel::class.java))
        } else {
            return arrayListOf()
        }
    }

    fun getInitSharedPreference(todayDate: LocalDate = LocalDate.now()): ArrayList<CalendarModel>? {
        val getGson = Gson()
        val calendarModel = preferences.getString(CALENDAR_MODEL.createInitDateKey(todayDate), "")
        println("song--0  ${calendarModel}")
        println(calendarModel)
        return if (!calendarModel.isNullOrEmpty()) {
            println("song--1")
            println(calendarModel)
            arrayListOf(getGson.fromJson(calendarModel, CalendarModel::class.java))
        } else {
            println("song--2")
            return arrayListOf()
        }
    }
}