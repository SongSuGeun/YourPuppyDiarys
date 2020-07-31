package com.example.your_puppy_diary.main.data

import android.os.Parcel
import android.os.Parcelable

data class CalendarModel(
    val year: Int,
    val month: String,
    val day: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(year)
        dest?.writeString(month)
        dest?.writeInt(day)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CalendarModel> {
        override fun createFromParcel(parcel: Parcel): CalendarModel {
            return CalendarModel(parcel)
        }

        override fun newArray(size: Int): Array<CalendarModel?> {
            return arrayOfNulls(size)
        }
    }
}