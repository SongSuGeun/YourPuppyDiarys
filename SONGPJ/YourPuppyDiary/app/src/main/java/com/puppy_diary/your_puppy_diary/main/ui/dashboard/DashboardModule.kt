package com.puppy_diary.your_puppy_diary.main.ui.dashboard

import dagger.Module
import dagger.Provides

@Module
class DashboardModule {
    @Provides
    fun provideDashboardPresenter(): DashboardPresenter {
        return DashboardPresenterImpl()
    }
}