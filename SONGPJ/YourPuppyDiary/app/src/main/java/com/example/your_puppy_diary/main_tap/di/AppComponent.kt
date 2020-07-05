package com.example.your_puppy_diary.main_tap.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BindingModule::class]
)
interface AppComponent : AndroidInjector<AppApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication)

        fun build(): AppComponent
    }

}