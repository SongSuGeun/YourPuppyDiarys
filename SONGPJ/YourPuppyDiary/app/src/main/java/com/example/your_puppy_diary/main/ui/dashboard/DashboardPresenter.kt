package com.example.your_puppy_diary.main.ui.dashboard

import com.example.your_puppy_diary.main.data.CalendarModel
import javax.inject.Inject

interface DashboardPresenter {
    fun takeView(view: DashboardFragment)
    fun onClickCalender(year: Int, month: String, day: Int)
}

class DashboardPresenterImpl @Inject constructor() : DashboardPresenter {

    lateinit var calendarModel: CalendarModel
    lateinit var view: DashboardView

    override fun takeView(view: DashboardFragment) {
        this.view = view
    }

    override fun onClickCalender(year: Int, month: String, day: Int) {
        this.calendarModel = CalendarModel(year, month, day)
        view.navigateCalenderMemo(calendarModel)
    }
}