package com.droid.bigbang.data

import androidx.lifecycle.MutableLiveData
import com.droid.bigbang.models.PhotoParseResult

interface DataRepository {
    fun getPhotos(path: Int, photoLiveData: MutableLiveData<PhotoParseResult>): MutableLiveData<PhotoParseResult>
}