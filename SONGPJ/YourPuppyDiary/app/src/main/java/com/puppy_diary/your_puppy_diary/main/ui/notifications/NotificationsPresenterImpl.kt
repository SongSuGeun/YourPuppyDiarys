package com.puppy_diary.your_puppy_diary.main.ui.notifications

import java.util.*
import javax.inject.Inject

interface NotificationPresenter {
    fun takeView(view: NotificationView)
    fun dropView()
    fun onclickAlarmStartButton(hour: Int, minute: Int)
    fun onClickResetAlarmButton()
}

class NotificationsPresenterImpl @Inject constructor() : NotificationPresenter {

    var view: NotificationView? = null

    override fun takeView(view: NotificationView) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun onclickAlarmStartButton(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            this.timeInMillis = System.currentTimeMillis()
            this.set(Calendar.HOUR_OF_DAY, hour)
            this.set(Calendar.MINUTE, minute)
            this.set(Calendar.SECOND, 0)
        }
        // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1)
        }
        view?.startAlarm(hour, minute, calendar)
    }

    override fun onClickResetAlarmButton() {
        view?.resetAlarm()
    }
}