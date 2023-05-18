package com.mra.rentcar.model

import android.os.Parcel
import android.os.Parcelable

data class CarModel(
    val username: String?,
    val carId: Int?,
    val carImage: String?,
    val make: String?,
    val model: String?,
    val year: Int?,
    val price: Int?,
    val location: String?,
): Parcelable {

    constructor(parcel: Parcel) : this (
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    companion object CREATOR : Parcelable.Creator<CarModel> {
        override fun createFromParcel(parcel: Parcel): CarModel {
            return CarModel(parcel)
        }

        override fun newArray(size: Int): Array<CarModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(username)
        dest.writeString(carImage)
        dest.writeInt(carId ?: -1)
        dest.writeString(make)
        dest.writeString(model)
        dest.writeInt(year ?: -1)
        dest.writeInt(price ?: -1)
        dest.writeString(location)
    }



}
