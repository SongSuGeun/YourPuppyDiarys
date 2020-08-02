package com.example.your_puppy_diary.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoActivity
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dashboard_frag.*
import javax.inject.Inject

interface DashboardView {
    fun navigateCalenderMemo(calendarModel: CalendarModel)
}

class DashboardFragment : DaggerFragment(), DashboardView {

    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)

        calender_event.initCalderItemClickCallback {
            presenter.onClickCalender(it.year, it.month, it.day)
        }

        addCalendarMemo.setOnClickListener {
            presenter.onClickCalenderMemo()
        }
    }

    override fun navigateCalenderMemo(calendarModel: CalendarModel) {
        startActivity(CalendarMemoActivity.getIntent(requireContext(), calendarModel))
    }
}
