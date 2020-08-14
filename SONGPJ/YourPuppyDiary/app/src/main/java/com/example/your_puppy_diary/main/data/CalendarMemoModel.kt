package com.example.your_puppy_diary.main.data

import android.os.Parcel
import android.os.Parcelable

data class CalendarMemoModel(
    val title: String,
    val content: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(content)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CalendarMemoModel> {
        override fun createFromParcel(parcel: Parcel): CalendarMemoModel {
            return CalendarMemoModel(parcel)
        }

        override fun newArray(size: Int): Array<CalendarMemoModel?> {
            return arrayOfNulls(size)
        }
    }
}

