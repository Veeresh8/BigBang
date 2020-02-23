package com.droid.bigbang.data

import androidx.lifecycle.MutableLiveData
import com.droid.bigbang.models.PhotoParseResult

class DataRepositoryImpl: DataRepository {
    override fun getPhotos(path: Int, photoLiveData: MutableLiveData<PhotoParseResult>): MutableLiveData<PhotoParseResult> {
        return photoLiveData
    }
}