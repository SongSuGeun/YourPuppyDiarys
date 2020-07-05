package com.example.your_puppy_diary.main_tap.di

import com.example.your_puppy_diary.main_tap.MainTopActivity
import com.example.your_puppy_diary.main_tap.ui.notifications.NotificationModule
import com.example.your_puppy_diary.main_tap.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun mainTopActivity(): MainTopActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationFragment(): NotificationsFragment
}