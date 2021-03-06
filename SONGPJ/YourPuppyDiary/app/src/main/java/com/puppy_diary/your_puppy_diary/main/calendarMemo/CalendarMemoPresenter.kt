package com.puppy_diary.your_puppy_diary.main.calendarMemo

import com.puppy_diary.your_puppy_diary.main.MySharedPreferences
import com.puppy_diary.your_puppy_diary.main.data.CalendarModel
import javax.inject.Inject

interface CalendarMemoPresenter {
    fun takeView(view: CalendarMemoView)
    fun dropView()
    fun onClickCancelButton()
    fun onClickSaveButton(title: String, content: String)
    fun initDate(calendarModel: CalendarModel, sharedPreferences: MySharedPreferences)
}

class CalendarMemoPresenterImpl @Inject constructor() : CalendarMemoPresenter {

    lateinit var calendarModel: CalendarModel
    lateinit var sharedPreferences: MySharedPreferences
    private var view: CalendarMemoView? = null

    override fun takeView(view: CalendarMemoView) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun onClickCancelButton() {
        view?.finishView(calendarModel)
    }

    override fun initDate(calendarModel: CalendarModel, sharedPreferences: MySharedPreferences) {
        this.calendarModel = calendarModel
        this.sharedPreferences = sharedPreferences
    }

    override fun onClickSaveButton(title: String, content: String) {
        if (title.isEmpty() || content.isEmpty()) {
            view?.showWarningDialog()
        } else {
            with(calendarModel) {
                this.title = title
                this.content = content
            }
            sharedPreferences.editSharedPreference(calendarModel)
            view?.finishView(calendarModel)
        }
    }
}