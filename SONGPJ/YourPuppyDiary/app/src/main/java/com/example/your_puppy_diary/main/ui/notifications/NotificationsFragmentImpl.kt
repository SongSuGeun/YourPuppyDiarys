package com.example.your_puppy_diary.main.ui.notifications

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.AlarmReceiver
import com.example.your_puppy_diary.main.ui.toast.basicAlarmToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.notifications_frag.*
import java.util.*
import javax.inject.Inject

interface NotificationView {
    fun startAlarm(hour: Int, minute: Int, calendar: Calendar)
    fun resetAlarm()
}

class NotificationsFragmentImpl : DaggerFragment(), NotificationView {

    companion object {
        private const val DAILY_ALARM = "daily_alarm"
        private const val ALARM_HOUR = "alarm_hour"
        private const val ALARM_MINUTE = "alarm_minute"
    }

    @Inject
    lateinit var presenter: NotificationPresenter

    private lateinit var alarmManager: AlarmManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notifications_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        settingTimePicker()

        settingButton.setOnClickListener {
            presenter.onclickAlarmStartButton(
                notificationTimePicker.hour,
                notificationTimePicker.minute
            )
        }

        cancelAlarmButton.setOnClickListener {
            presenter.onClickResetAlarmButton()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun startAlarm(hour: Int, minute: Int, calendar: Calendar) {
        val dateTimeText = resources.getString(R.string.setting_time, hour, minute)
        basicAlarmToast(requireContext(), dateTimeText)

        requireContext().getSharedPreferences(DAILY_ALARM, MODE_PRIVATE).edit().apply {
            putInt(ALARM_HOUR, hour)
            putInt(ALARM_MINUTE, minute)
            apply()
        }
        diaryNotification(calendar)
    }

    override fun resetAlarm() {
        requireContext().getSharedPreferences(DAILY_ALARM, MODE_PRIVATE).edit().clear().apply()
        val alarmIntent = Intent(requireContext(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, alarmIntent, 0)
        alarmManager.cancel(pendingIntent)
        basicAlarmToast(requireContext(), null)
    }

    private fun settingTimePicker() {
        notificationTimePicker.setIs24HourView(true)
        val sharedPreferences =
            requireContext().getSharedPreferences(DAILY_ALARM, MODE_PRIVATE)
        val hour = sharedPreferences.getInt(ALARM_HOUR, 0)
        val minute = sharedPreferences.getInt(ALARM_MINUTE, 0)
        if (hour != 0 || minute != 0) {
            val dateTimeText = resources.getString(R.string.setting_time, hour, minute)
            basicAlarmToast(requireContext(), dateTimeText)
        }
    }

    private fun diaryNotification(calendar: Calendar) {
        val alarmIntent = Intent(requireContext(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, alarmIntent, 0)
        // 사용자가 매일 알람을 허용했다면
        alarmManager.setRepeating(
            RTC_WAKEUP,
            calendar.timeInMillis,
            INTERVAL_DAY,
            pendingIntent
        )
        alarmManager.setExactAndAllowWhileIdle(
            RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}
