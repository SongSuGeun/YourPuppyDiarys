package com.example.your_puppy_diary.main_tap.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
//
//class AppApplication {
//}

class AppApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}