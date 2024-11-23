package com.example.filminfo.Model

import android.os.Parcel
import android.os.Parcelable

data class Film(
    var id: Int,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double,
    val imageUrl: String,
    val description: String,
    val genres: List<String>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(localizedName)
        parcel.writeString(name)
        parcel.writeInt(year)
        parcel.writeDouble(rating)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}

