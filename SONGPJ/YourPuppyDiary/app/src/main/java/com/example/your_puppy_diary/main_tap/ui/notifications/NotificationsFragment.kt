package com.example.your_puppy_diary.main_tap.ui.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main_tap.AlarmReceiver
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.util.*
import kotlin.math.min

class NotificationsFragment : Fragment() {

    lateinit var alarmManager: AlarmManager
    private lateinit var alarmIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter = NotificationsPresenterImpl()

        presenter.takeView(this)

        settingButton.setOnClickListener {
            presenter.onclickAlarmStartButton(
                notificationTimePicker.hour,
                notificationTimePicker.minute
            )
        }

        cancelAlarmButton.setOnClickListener {
            presenter.onClickResetAlarmButton(
                notificationTimePicker.hour,
                notificationTimePicker.minute
            )

        }
    }

    fun settingAlarm(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2020)
            this.set(Calendar.MONTH, 7)
            this.set(Calendar.DAY_OF_YEAR, 1)
            this.set(Calendar.HOUR_OF_DAY, hour)
            this.set(Calendar.MINUTE, minute)
            this.set(Calendar.SECOND, 0)
        }
        val alarmIntent = Intent(requireContext(), AlarmReceiver::class.java)
//        val calendar = Calendar.getInstance().apply {
//            set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hour, minute)
//        }
//        alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
//        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//        intent.apply {
//            this.putExtra(AlarmClock.EXTRA_HOUR, hour)
//            this.putExtra(AlarmClock.EXTRA_MINUTES, minute)
//            this.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//        }
//        startActivity(intent)
    }

    fun resetAlarm(hour: Int, minute: Int) {
        val intent = Intent(AlarmClock.ACTION_DISMISS_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour)
        intent.putExtra(AlarmClock.EXTRA_HOUR, minute)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        startActivity(intent)
    }
}
