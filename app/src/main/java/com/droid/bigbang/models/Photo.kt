package com.droid.bigbang.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.lang.Exception

@Parcelize
data class Photo (val copyright: String?,
                  val date: String?,
                  val explanation: String?,
                  @SerializedName("hdurl") val originalURL: String?,
                  @SerializedName("media_type") val mediaType: String?,
                  @SerializedName("service_version") val serviceVersion: String?,
                  val title: String?,
                  val url: String?): Parcelable

sealed class PhotoParseResult {
    class PhotosList(val photoList: List<Photo>) : PhotoParseResult()
    class PhotoParseException(val parseException: Exception) : PhotoParseResult()
}