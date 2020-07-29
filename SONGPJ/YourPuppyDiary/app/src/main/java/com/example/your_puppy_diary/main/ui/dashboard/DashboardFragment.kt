package com.example.your_puppy_diary.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)

        calender_event.initCalderItemClickCallback {
            presenter.onClickCalender(it.year, it.month, it.day)
        }
    }
}
