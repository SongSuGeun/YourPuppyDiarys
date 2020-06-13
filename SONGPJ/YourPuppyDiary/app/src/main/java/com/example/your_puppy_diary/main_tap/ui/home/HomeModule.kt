package com.example.your_puppy_diary.main_tap.ui.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesHomePresenter(): HomePresenterImpl {
        return HomePresenterImpl()
    }

}