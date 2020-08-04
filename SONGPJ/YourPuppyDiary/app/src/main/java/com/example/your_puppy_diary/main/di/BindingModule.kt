package com.example.your_puppy_diary.main.di

import com.example.your_puppy_diary.main.MainTopActivity
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoActivity
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoFragment
import com.example.your_puppy_diary.main.calendarMemo.CalendarMemoModule
import com.example.your_puppy_diary.main.ui.dashboard.DashboardFragment
import com.example.your_puppy_diary.main.ui.dashboard.DashboardModule
import com.example.your_puppy_diary.main.ui.home.HomeFragment
import com.example.your_puppy_diary.main.ui.home.HomeModule
import com.example.your_puppy_diary.main.ui.notifications.NotificationModule
import com.example.your_puppy_diary.main.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun mainTopActivity(): MainTopActivity

    @ContributesAndroidInjector
    abstract fun calendarMemo(): CalendarMemoActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationFragment(): NotificationsFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun dashBoardFragment(): DashboardFragment

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [CalendarMemoModule::class])
    abstract fun calendarMemoFragment(): CalendarMemoFragment
}