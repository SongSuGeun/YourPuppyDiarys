package com.example.your_puppy_diary.main_tap.ui.notifications

interface NotificationPresenter {
    fun onclickAlarmStartButton(hour: Int, minute: Int)
    fun onClickResetAlarmButton(hour: Int, minute: Int)
}

class NotificationsPresenterImpl : NotificationPresenter {

    lateinit var view: NotificationsFragment

    fun takeView(view: NotificationsFragment) {
        this.view = view
    }

    override fun onclickAlarmStartButton(hour: Int, minute: Int) {
        view.settingAlarm(hour, minute)
    }

    override fun onClickResetAlarmButton(hour: Int, minute: Int) {
        view.resetAlarm(hour, minute)
    }
}