package com.example.your_puppy_diary.main.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.*
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
    fun showSelectCalenderMemo(date: String)
    fun removeCalendarMemo(date: String, position: Int)
}

class DashboardFragment : DaggerFragment(), DashboardView {

    companion object {
        private const val CALENDAR_MEMO = "calendar_memo"
        private const val REQUEST_CODE = 0
        private const val REQUEST_OK = 1
    }

    @Inject
    lateinit var presenter: DashboardPresenter
    private var calendarModelList: MutableList<CalendarModel>? = mutableListOf()
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calender_event.initCalderItemClickCallback {
            presenter.onClickCalender(it.year, it.monthNumber + 1, it.day)
        }

        addCalendarMemo.setOnClickListener {
            presenter.onClickCalenderMemo()
        }
        mySharedPreferences = MySharedPreferences(requireContext())

        val date = createInitDateKey(LocalDate.now())
        recyclerCalenderMemo.adapter ?: showSelectCalenderMemo(date)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun navigateCalenderMemo(calendarModel: CalendarModel) {
        startActivityForResult(
            CalendarMemoActivity.getIntent(requireContext(), calendarModel), REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == REQUEST_OK) {
            val calendarModel = data?.getParcelableExtra<CalendarModel>(CALENDAR_MEMO)
            calendarModel?.let {
                val date = createDateKey(calendarModel)
                showSelectCalenderMemo(date)
            }
        }
    }

    override fun showSelectCalenderMemo(date: String) {
        Single.just(mySharedPreferences.getSharedPreference(date))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MutableList<CalendarModel>?>() {
                override fun onSuccess(result: MutableList<CalendarModel>) {
                    calendarModelList = result
                    recyclerCalenderMemo.layoutManager = LinearLayoutManager(requireContext())
                    recyclerCalenderMemo.setHasFixedSize(true)
                    recyclerCalenderMemo.isNestedScrollingEnabled = false

                    recyclerCalenderMemo.adapter = DashboardAdapter(
                        requireContext(),
                        calendarModelList,
                        object : DashboardAdapter.OnClickDashBoardListener {
                            override fun onClickRemoveCalendarMemo(view: View, position: Int) {
                                view.setOnClickListener {
                                    presenter.onClickRemoveCalendarMemoButton(date, position)
                                }
                            }
                        }
                    )
                }

                override fun onError(e: Throwable) {
                    Timber.d(e)
                }
            })
    }

    override fun removeCalendarMemo(date: String, position: Int) {
        mySharedPreferences.removeSharedPreference(date, position)
        calendarModelList?.removeAt(position)
        recyclerCalenderMemo.adapter?.notifyDataSetChanged()
    }
}