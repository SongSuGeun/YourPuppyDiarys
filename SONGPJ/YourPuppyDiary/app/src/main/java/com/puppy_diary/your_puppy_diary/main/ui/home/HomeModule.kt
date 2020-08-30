package com.puppy_diary.your_puppy_diary.main.ui.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun providesHomePresenter(): HomePresenter {
        return HomePresenterImpl()
    }
}