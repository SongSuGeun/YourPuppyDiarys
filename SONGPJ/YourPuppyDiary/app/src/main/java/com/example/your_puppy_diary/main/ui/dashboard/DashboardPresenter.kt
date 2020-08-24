package com.example.your_puppy_diary.main.ui.dashboard

import com.example.your_puppy_diary.main.createDateKey
import com.example.your_puppy_diary.main.data.CalendarModel
import java.time.LocalDate
import javax.inject.Inject

interface DashboardPresenter {
    fun takeView(view: DashboardFragment)
    fun onClickCalender(year: Int, month: Int, day: Int)
    fun onClickCalenderMemo()
    fun dropView()
    fun onClickRemoveCalendarMemoButton(date: String, position: Int)
}

class DashboardPresenterImpl @Inject constructor() : DashboardPresenter {

    companion object {
        private const val CALENDAR_MODEL = "calendarModel"
    }

    lateinit var calendarModel: CalendarModel
    var view: DashboardView? = null

    override fun takeView(view: DashboardFragment) {
        this.view = view
        initTodayDate()
    }

    override fun dropView() {
        view = null
    }

    override fun onClickRemoveCalendarMemoButton(date: String, position: Int) {
        view?.removeCalendarMemo(date, position)
    }

    override fun onClickCalender(year: Int, month: Int, day: Int) {
        this.calendarModel = CalendarModel(year, month, day)
        val date = createDateKey(calendarModel)
        view?.showSelectCalenderMemo(date)
    }

    override fun onClickCalenderMemo() {
        view?.navigateCalenderMemo(calendarModel)
    }

    private fun initTodayDate() {
        val todayDate = LocalDate.now()
        this.calendarModel =
            CalendarModel(todayDate.year, todayDate.monthValue, todayDate.dayOfMonth)
    }
}