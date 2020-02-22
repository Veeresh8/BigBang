package com.droid.bigbang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.bigbang.data.DataRepository
import com.droid.bigbang.models.PhotoParseResult

class PhotoViewModel constructor(private val dataRepository: DataRepository): ViewModel() {
    private val photoLiveData = MutableLiveData<PhotoParseResult>()

    fun fetchPhotos(path: String): LiveData<PhotoParseResult> {
        return dataRepository.getPhotos(path, photoLiveData)
    }
}