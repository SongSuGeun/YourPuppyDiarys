package com.example.your_puppy_diary.main_tap.ui.notifications

import dagger.Module
import dagger.Provides

@Module
class NotificationModule {

    @Provides
    fun provideNotificationPresenter(): NotificationsPresenterImpl {
        return NotificationsPresenterImpl()
    }
}