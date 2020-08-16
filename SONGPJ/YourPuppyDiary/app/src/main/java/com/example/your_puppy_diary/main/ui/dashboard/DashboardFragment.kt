package com.example.your_puppy_diary.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
            presenter.onClickCalender(it.year, it.monthNumber + 1, it.day)
        }

        addCalendarMemo.setOnClickListener {
            presenter.onClickCalenderMemo()
        }

        val model = arrayListOf(
            CalendarModel(11, 22, 33, "aa", "bb"),
            CalendarModel(11, 22, 33, "bb ", "cc")
        )

        val adapter = DashboardAdapter(requireContext(), model)
        recyclerCalenderMemo.adapter = adapter
        recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
        recyclerCalenderMemo.setHasFixedSize(true)
    }

    override fun navigateCalenderMemo(calendarModel: CalendarModel) {
        startActivity(CalendarMemoActivity.getIntent(requireContext(), calendarModel))
    }
}
