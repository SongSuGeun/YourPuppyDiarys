package com.example.your_puppy_diary.main.calendarMemo

import com.example.your_puppy_diary.main.MySharedPreferences
import com.example.your_puppy_diary.main.data.CalendarModel
import javax.inject.Inject

interface CalendarMemoPresenter {
    fun takeView(view: CalendarMemoView)
    fun dropView()
    fun onClickCancelButton()
    fun onClickSaveButton(title: String, content: String)
    fun initDate(calendarModel: CalendarModel, sharedPreferences: MySharedPreferences)
}

class CalendarMemoPresenterImpl @Inject constructor() : CalendarMemoPresenter {

    private var view: CalendarMemoView? = null
    lateinit var calendarModel: CalendarModel
    lateinit var sharedPreferences: MySharedPreferences

    override fun takeView(view: CalendarMemoView) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun onClickCancelButton() {
        view?.finishView()
    }

    override fun initDate(calendarModel: CalendarModel, sharedPreferences: MySharedPreferences) {
        this.calendarModel = calendarModel
        this.sharedPreferences = sharedPreferences
    }

    override fun onClickSaveButton(title: String, content: String) {
        with(calendarModel) {
            this.title = title
            this.content = content
        }
        sharedPreferences.myCalendar = calendarModel
        view?.finishView()
    }
}