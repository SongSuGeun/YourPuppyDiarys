package com.example.your_puppy_diary.main.data

import android.os.Parcel
import android.os.Parcelable

data class CalendarModel(
    val year: Int,
    val month: Int,
    val day: Int,
    var title: String = "",
    var content: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(year)
        dest?.writeInt(month)
        dest?.writeInt(day)
        dest?.writeString(title)
        dest?.writeString(content)
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