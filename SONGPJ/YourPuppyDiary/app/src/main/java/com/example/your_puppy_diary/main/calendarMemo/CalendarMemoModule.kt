package com.example.your_puppy_diary.main.calendarMemo

import dagger.Module
import dagger.Provides

@Module
class CalendarMemoModule {
    @Provides
    fun provideCalendarMemoPresent(): CalendarMemoPresenter {
        return CalendarMemoPresenterImpl()
    }
}