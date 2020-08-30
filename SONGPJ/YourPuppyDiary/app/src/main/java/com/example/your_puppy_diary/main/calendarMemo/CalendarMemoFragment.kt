package com.example.your_puppy_diary.main.calendarMemo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.MySharedPreferences
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.calendar_frag.*
import javax.inject.Inject

interface CalendarMemoView {
    fun finishView(calendarMemo: CalendarModel?)
    fun showWarningDialog()
}

class CalendarMemoFragment : DaggerFragment(), CalendarMemoView {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        private const val REQUEST_OK = 1
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
        val sharedPreferences = MySharedPreferences(requireContext())
        val calendarModel = arguments?.getParcelable<CalendarModel>(CALENDAR_MEMO)

        calendarModel?.let {
            todayDate.text = initialSelectDate(calendarModel)
            presenter.initDate(calendarModel, sharedPreferences)
        }

        calendarMemoCancelButton.setOnClickListener {
            presenter.onClickCancelButton()
        }

        calendarMemoSaveButton.setOnClickListener {
            presenter.onClickSaveButton(insertTitle.text.toString(), insertContent.text.toString())
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

    override fun finishView(calendarMemo: CalendarModel?) {
        val intent = Intent().apply {
            this.putExtra(CALENDAR_MEMO, calendarMemo)
        }
        requireActivity().setResult(REQUEST_OK, intent)
        requireActivity().finish()
    }

    override fun showWarningDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.need_to_title)
            .setMessage(R.string.need_to_content)
            .setPositiveButton(R.string.ok) { _, _ -> }
            .show()
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