package com.droid.bigbang.models

import com.google.gson.annotations.SerializedName

data class Photo (val copyright: String,
                  val date: String,
                  val explanation: String,
                  @SerializedName("hdurl") val originalURL: String,
                  @SerializedName("media_type") val mediaType: String,
                  @SerializedName("service_version") val serviceVersion: String,
                  val title: String,
                  val url: String)

sealed class PhotoParseResult {
    class Photo(val photoList: List<Photo>) : PhotoParseResult()
    class PhotoParseException(val message: String) : PhotoParseResult()
}