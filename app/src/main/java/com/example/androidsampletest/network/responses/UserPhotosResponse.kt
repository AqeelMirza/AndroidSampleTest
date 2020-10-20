package com.example.androidsampletest.network.responses

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class UserPhotosResponse(
    val id: Int?,
    val albumId: Int?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(albumId)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserPhotosResponse> {
        override fun createFromParcel(parcel: Parcel): UserPhotosResponse {
            return UserPhotosResponse(parcel)
        }

        override fun newArray(size: Int): Array<UserPhotosResponse?> {
            return arrayOfNulls(size)
        }
    }
}