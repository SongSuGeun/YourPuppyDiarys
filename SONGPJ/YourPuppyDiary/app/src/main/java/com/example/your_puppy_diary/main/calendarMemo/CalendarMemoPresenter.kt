package com.example.your_puppy_diary.main.calendarMemo

import com.example.your_puppy_diary.main.data.CalendarModel
import javax.inject.Inject

interface CalendarMemoPresenter {
    fun takeView(view: CalendarMemoView)
    fun onClickCancelButton()
    fun onClickSaveButton(title: String, content: String)
    fun initDate(calendarModel: CalendarModel)
}

class CalendarMemoPresenterImpl @Inject constructor() : CalendarMemoPresenter {

    lateinit var view: CalendarMemoView
    lateinit var calendarModel: CalendarModel

    override fun takeView(view: CalendarMemoView) {
        this.view = view
    }

    override fun onClickCancelButton() {
        view.finishView()
    }

    override fun onClickSaveButton(title: String, content: String) {

    }

    override fun initDate(calendarModel: CalendarModel) {
        this.calendarModel = calendarModel
    }
}