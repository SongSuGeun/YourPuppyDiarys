package com.example.your_puppy_diary.main.di

import com.example.your_puppy_diary.main.MainTopActivity
import com.example.your_puppy_diary.main.ui.notifications.NotificationModule
import com.example.your_puppy_diary.main.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun mainTopActivity(): MainTopActivity

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    abstract fun notificationFragment(): NotificationsFragment
}