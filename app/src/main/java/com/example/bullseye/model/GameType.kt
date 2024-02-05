package com.example.bullseye.model

import android.os.Parcel
import android.os.Parcelable

data class GameType(val playerList: List<String>, val roundCount: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(playerList)
        parcel.writeInt(roundCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameType> {
        override fun createFromParcel(parcel: Parcel): GameType {
            return GameType(parcel)
        }

        override fun newArray(size: Int): Array<GameType?> {
            return arrayOfNulls(size)
        }
    }
}
