package com.example.your_puppy_diary.main.ui.dashboard

import com.example.your_puppy_diary.main.data.CalendarModel
import java.time.LocalDate
import javax.inject.Inject

interface DashboardPresenter {
    fun takeView(view: DashboardFragment)
    fun onClickCalender(year: Int, month: Int, day: Int)
    fun onClickCalenderMemo()
}

class DashboardPresenterImpl @Inject constructor() : DashboardPresenter {

    lateinit var calendarModel: CalendarModel
    lateinit var view: DashboardView

    override fun takeView(view: DashboardFragment) {
        this.view = view
        val todayDate = LocalDate.now()
        this.calendarModel =
            CalendarModel(todayDate.year, todayDate.monthValue, todayDate.dayOfMonth)
    }

    override fun onClickCalender(year: Int, month: Int, day: Int) {
        this.calendarModel = CalendarModel(year, month, day)
    }

    override fun onClickCalenderMemo() {
        view.navigateCalenderMemo(calendarModel)
    }
}