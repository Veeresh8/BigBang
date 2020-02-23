package com.droid.bigbang.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.droid.bigbang.R
import com.droid.bigbang.models.Photo
import com.droid.bigbang.models.PhotoParseResult
import com.google.gson.Gson

class DataRepositoryImpl(private val context: Context, private val gson: Gson): DataRepository {
    override fun getPhotos(path: Int): MutableLiveData<PhotoParseResult> {
        val photoLiveData = MutableLiveData<PhotoParseResult>()
        try {
            val photosJSON = context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
            val photoList = gson.fromJson(photosJSON, Array<Photo>::class.java).toList()
            photoLiveData.postValue(PhotoParseResult.PhotosList(photoList))
        } catch (exception: Exception) {
            photoLiveData.postValue(PhotoParseResult.PhotoParseException(exception))
        }

        return photoLiveData
    }
}