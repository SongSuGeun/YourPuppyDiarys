package com.example.your_puppy_diary.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

interface HomeFragmentView {
    fun requestData()
}

class HomeFragment : DaggerFragment(), HomeFragmentView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestData()
    }

    override fun requestData() {

    }
}
