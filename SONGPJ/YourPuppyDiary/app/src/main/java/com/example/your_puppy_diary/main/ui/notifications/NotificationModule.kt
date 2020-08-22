package com.example.your_puppy_diary.main.ui.notifications

import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationPresenter(): NotificationPresenter {
        return NotificationsPresenterImpl()
    }
}