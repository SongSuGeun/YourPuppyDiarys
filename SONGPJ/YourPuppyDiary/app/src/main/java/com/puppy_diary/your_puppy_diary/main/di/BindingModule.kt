package com.puppy_diary.your_puppy_diary.main.di

import com.puppy_diary.your_puppy_diary.main.MainTopActivity
import com.puppy_diary.your_puppy_diary.main.calendarMemo.CalendarMemoActivity
import com.puppy_diary.your_puppy_diary.main.calendarMemo.CalendarMemoFragment
import com.puppy_diary.your_puppy_diary.main.calendarMemo.CalendarMemoModule
import com.puppy_diary.your_puppy_diary.main.ui.dashboard.DashboardFragment
import com.puppy_diary.your_puppy_diary.main.ui.dashboard.DashboardModule
import com.puppy_diary.your_puppy_diary.main.ui.home.HomeFragment
import com.puppy_diary.your_puppy_diary.main.ui.home.HomeModule
import com.puppy_diary.your_puppy_diary.main.ui.notifications.NotificationModule
import com.puppy_diary.your_puppy_diary.main.ui.notifications.NotificationsFragmentImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun mainTopActivity(): MainTopActivity

    @ContributesAndroidInjector
    abstract fun calendarMemo(): CalendarMemoActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationFragment(): NotificationsFragmentImpl

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun dashBoardFragment(): DashboardFragment

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [CalendarMemoModule::class])
    abstract fun calendarMemoFragment(): CalendarMemoFragment
}