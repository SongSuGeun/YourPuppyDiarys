package com.example.your_puppy_diary.main.calendarMemo

import javax.inject.Inject

interface CalendarMemoPresenter {
    fun takeView(view: CalendarMemoView)
    fun onClickCancelButton()
    fun onClickSaveButton()

}

class CalendarMemoPresenterImpl @Inject constructor(): CalendarMemoPresenter {

    lateinit var view: CalendarMemoView

    override fun takeView(view: CalendarMemoView) {
        this.view = view
    }

    override fun onClickCancelButton() {
        view.finishView()
    }

    override fun onClickSaveButton() {
        view.saveCalendarMemo()
    }
}