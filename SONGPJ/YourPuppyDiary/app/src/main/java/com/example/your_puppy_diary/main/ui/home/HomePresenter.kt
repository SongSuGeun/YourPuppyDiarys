package com.example.your_puppy_diary.main.ui.home

interface HomePresenter {
    fun takeView(view: HomeFragmentView)
    fun dropView()
    fun onClickAddImageButton()
    fun onClickRemoveImageButton(dogImageFile: String?)
}

class HomePresenterImpl : HomePresenter {

    private var view: HomeFragmentView? = null

    override fun takeView(view: HomeFragmentView) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun onClickRemoveImageButton(dogImageFile: String?) {
        view?.removeImage(dogImageFile)
    }

    override fun onClickAddImageButton() {
        view?.takePicture()
    }
}