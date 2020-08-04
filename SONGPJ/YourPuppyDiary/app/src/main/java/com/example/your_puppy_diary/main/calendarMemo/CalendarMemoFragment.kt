package com.example.your_puppy_diary.main.calendarMemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.calendar_frag.*
import javax.inject.Inject

interface CalendarMemoView {
    fun finishView()
    fun saveCalendarMemo()
}

class CalendarMemoFragment : DaggerFragment(), CalendarMemoView {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        fun newInstance(calendarMemo: CalendarModel) = CalendarMemoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CALENDAR_MEMO, calendarMemo)
            }
        }
    }

    @Inject
    lateinit var presenter: CalendarMemoPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calendar_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)

        calendarMemoCancelButton.setOnClickListener {
            presenter.onClickCancelButton()
        }

        calendarMemoSaveButton.setOnClickListener {
            presenter.onClickSaveButton()
        }
    }

    override fun finishView() {
        requireActivity().finish()
    }

    override fun saveCalendarMemo() {
        // TODO modelをSAVEしてViewをFInishすること。
        requireActivity().finish()
    }
}