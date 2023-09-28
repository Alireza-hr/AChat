package com.alirezahr.emoji.core.emoji.gif

import android.os.Parcel
import android.os.Parcelable


class Gif : Parcelable {

    private val gifUrl: String

    private val previewGifUrl: String?

    private val mp4Url: String?

    constructor(gifUrl: String, previewGifUrl: String?, mp4Url: String?) {
        this.gifUrl = gifUrl
        this.previewGifUrl = previewGifUrl
        this.mp4Url = mp4Url
    }

    constructor(gifUrl: String) {
        this.gifUrl = gifUrl
        previewGifUrl = null
        mp4Url = null
    }

    constructor(`in`: Parcel) {
        previewGifUrl = `in`.readString()
        gifUrl = `in`.readString()!!
        mp4Url = `in`.readString()
    }

    fun getPreviewGifUrl(): String {
        return previewGifUrl ?: gifUrl
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(previewGifUrl)
        dest.writeString(gifUrl)
        dest.writeString(mp4Url)
    }

    companion object CREATOR : Parcelable.Creator<Gif> {
        override fun createFromParcel(parcel: Parcel): Gif {
            return Gif(parcel)
        }

        override fun newArray(size: Int): Array<Gif?> {
            return arrayOfNulls(size)
        }
    }
}
