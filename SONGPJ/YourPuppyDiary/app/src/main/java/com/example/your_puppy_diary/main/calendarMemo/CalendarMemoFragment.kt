package com.example.your_puppy_diary.main.calendarMemo

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
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

        val calendarModel = arguments?.getParcelable<CalendarModel>(CALENDAR_MEMO)
        calendarModel?.let {
            todayDate.text = initialSelectDate(calendarModel)
            presenter.initDate(calendarModel)
        }

        calendarMemoCancelButton.setOnClickListener {
            presenter.onClickCancelButton()
        }

        calendarMemoSaveButton.setOnClickListener {
            presenter.onClickSaveButton(insertTitle.text.toString(), insertContent.text.toString())
        }
    }

    override fun finishView() {
        requireActivity().finish()
    }

    private fun initialSelectDate(calendarMemo: CalendarModel): SpannableString {
        val selectDate = getString(
            R.string.today_user_schedule_date,
            calendarMemo.year,
            calendarMemo.month,
            calendarMemo.day
        )
        val spannableString = SpannableString(selectDate)
        spannableString.setSpan(UnderlineSpan(), 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}