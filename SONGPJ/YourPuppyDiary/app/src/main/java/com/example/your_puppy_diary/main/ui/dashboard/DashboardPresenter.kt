package com.example.your_puppy_diary.main.ui.dashboard

import javax.inject.Inject

interface DashboardPresenter {
    fun takeView(view: DashboardFragment)
    fun onClickCalender(year: Int, month: String?, day: Int)
}

class DashboardPresenterImpl @Inject constructor() : DashboardPresenter {

    lateinit var view: DashboardFragment

    override fun takeView(view: DashboardFragment) {
        this.view = view
    }

    override fun onClickCalender(year: Int, month: String?, day: Int) {

    }
}