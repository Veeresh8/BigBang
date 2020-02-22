package com.droid.bigbang.data

import androidx.lifecycle.MutableLiveData
import com.droid.bigbang.models.PhotoParseResult

interface DataRepository {
    fun getPhotos(path: String, photoLiveData: MutableLiveData<PhotoParseResult>): MutableLiveData<PhotoParseResult>
}