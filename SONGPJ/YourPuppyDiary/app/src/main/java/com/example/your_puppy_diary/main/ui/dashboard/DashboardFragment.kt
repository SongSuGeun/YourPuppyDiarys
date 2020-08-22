package com.example.your_puppy_diary.main.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.MySharedPreferences
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoActivity
import com.example.your_puppy_diary.main.createDateKey
import com.example.your_puppy_diary.main.createInitDateKey
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.dashboard_frag.*
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

interface DashboardView {
    fun navigateCalenderMemo(calendarModel: CalendarModel)
    fun showSelectCalenderMemo(calendarModel: CalendarModel)
}

class DashboardFragment : DaggerFragment(), DashboardView {

    companion object {
        private const val CALENDAR_MODEL = "calendarModel"
        private const val CALENDAR_MEMO = "calendar_memo"
        private const val REQUEST_CODE = 0
        private const val REQUEST_OK = 1
    }

    @Inject
    lateinit var presenter: DashboardPresenter
    private var calendarModelList: MutableList<CalendarModel>? = mutableListOf()

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
        initAdapter()
    }

    override fun onResume() {
        presenter.takeView(this)
        super.onResume()
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun navigateCalenderMemo(calendarModel: CalendarModel) {
        startActivityForResult(
            CalendarMemoActivity.getIntent(requireContext(), calendarModel),
            REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == REQUEST_OK) {
                val calendarModel =
                    data?.getParcelableExtra<CalendarModel>(CALENDAR_MEMO)
                calendarModel?.let {
                    showSelectCalenderMemo(calendarModel)
                }
            }
        }
    }

    override fun showSelectCalenderMemo(calendarModel: CalendarModel) {
        val mySharedPreferences = MySharedPreferences(requireContext())
        val showDate = CALENDAR_MODEL.createDateKey(calendarModel)
        Single.just(mySharedPreferences.getSharedPreference(showDate))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MutableList<CalendarModel>?>() {
                override fun onSuccess(result: MutableList<CalendarModel>) {
                    calendarModelList = result
                    // Adapter処理
                    recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
                    recyclerCalenderMemo.setHasFixedSize(true)
                    recyclerCalenderMemo.isNestedScrollingEnabled = false

                    val adapter = DashboardAdapter(requireContext(), calendarModelList)
                    recyclerCalenderMemo.adapter = adapter
                    recyclerCalenderMemo.adapter?.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    Timber.d(e)
                }
            })
    }

    private fun initAdapter() {
        val mySharedPreferences = MySharedPreferences(requireContext())
        val initDate = CALENDAR_MODEL.createInitDateKey(LocalDate.now())

        Single.just(mySharedPreferences.getSharedPreference(initDate))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MutableList<CalendarModel>?>() {
                override fun onSuccess(result: MutableList<CalendarModel>) {
                    calendarModelList = result

                    // Adapter処理
                    recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
                    recyclerCalenderMemo.setHasFixedSize(true)
                    recyclerCalenderMemo.isNestedScrollingEnabled = false

                    val adapter = DashboardAdapter(requireContext(), calendarModelList)
                    recyclerCalenderMemo.adapter = adapter
                    recyclerCalenderMemo.adapter?.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    Timber.d(e)
                }
            })
    }
}