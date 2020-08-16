package com.example.your_puppy_diary.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_puppy_diary.R
import com.example.your_puppy_diary.main.MySharedPreferences
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoActivity
import com.example.your_puppy_diary.main.data.CalendarModel
import dagger.android.support.DaggerFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.dashboard_frag.*
import timber.log.Timber
import javax.inject.Inject

interface DashboardView {
    fun navigateCalenderMemo(calendarModel: CalendarModel)
    fun showSelectCalenderMemo(calendarModel: CalendarModel)
}

class DashboardFragment : DaggerFragment(), DashboardView {

    @Inject
    lateinit var presenter: DashboardPresenter
    private var calendarModelList: ArrayList<CalendarModel>? = arrayListOf()

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

    override fun navigateCalenderMemo(calendarModel: CalendarModel) {
        startActivity(CalendarMemoActivity.getIntent(requireContext(), calendarModel))
    }

    override fun showSelectCalenderMemo(calendarModel: CalendarModel) {
        val mySharedPreferences = MySharedPreferences(requireContext())
        Single.just(mySharedPreferences.getSharedPreference(calendarModel))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<CalendarModel>?>() {
                override fun onSuccess(result: ArrayList<CalendarModel>) {
                    calendarModelList = result
                    // Adapter処理
                    recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
                    recyclerCalenderMemo.setHasFixedSize(true)

                    val adapter = DashboardAdapter(requireContext(), calendarModelList)
                    recyclerCalenderMemo.adapter = adapter
                }
                override fun onError(e: Throwable) {
                    Timber.d(e)
                }
            })
    }

    private fun initAdapter() {
        val mySharedPreferences = MySharedPreferences(requireContext())

        Single.just(mySharedPreferences.getInitSharedPreference())
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<CalendarModel>?>() {
                override fun onSuccess(result: ArrayList<CalendarModel>) {
                    calendarModelList = result

                    // Adapter処理
                    recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
                    recyclerCalenderMemo.setHasFixedSize(true)

                    val adapter = DashboardAdapter(requireContext(), calendarModelList)
                    recyclerCalenderMemo.adapter = adapter
                }
                override fun onError(e: Throwable) {
                    Timber.d(e)
                }
            })
    }
}