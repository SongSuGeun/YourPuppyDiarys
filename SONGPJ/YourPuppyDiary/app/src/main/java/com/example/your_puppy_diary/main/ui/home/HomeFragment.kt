package com.example.your_puppy_diary.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.your_puppy_diary.R

interface HomeFragmentView {
    fun requestData()
}

class HomeFragment : Fragment(), HomeFragmentView {

//    @Inject
//    lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter.requestData()
    }

    override fun requestData() {

    }
}