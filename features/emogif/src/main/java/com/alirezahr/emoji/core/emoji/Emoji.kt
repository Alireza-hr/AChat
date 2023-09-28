package com.alirezahr.emoji.core.emoji

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


public class Emoji() : Parcelable {


    var unicode: String? = null

    var icon = -1

    constructor(unicode: String, icon: Int) : this() {
        this.unicode = unicode
        this.icon = icon
    }

    constructor(parcel: Parcel) : this() {
        icon = parcel.readInt()
        unicode = parcel.readString().toString()

        if (unicode!!.isEmpty()) {
            throw RuntimeException("in class -> EmoGif : unicode not must be a null")
        }
    }

    constructor(unicode: String?) : this() {
        this.unicode = unicode
    }

    companion object CREATOR : Parcelable.Creator<Emoji> {
        override fun createFromParcel(parcel: Parcel): Emoji {
            return Emoji(parcel)
        }

        override fun newArray(size: Int): Array<Emoji?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(icon)
        parcel.writeString(unicode)
    }

    override fun describeContents(): Int {
        return 0
    }
}
