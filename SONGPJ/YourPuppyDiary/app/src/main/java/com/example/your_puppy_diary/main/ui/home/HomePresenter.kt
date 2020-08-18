package com.example.your_puppy_diary.main.ui.home

interface HomePresenter {
    fun takeView(view: HomeFragmentView)
    fun onClickAddImageButton()
    fun dropView()
}

class HomePresenterImpl : HomePresenter {

    private var view: HomeFragmentView? = null

    override fun takeView(view: HomeFragmentView) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun onClickAddImageButton() {
        view?.takePicture()
    }
}