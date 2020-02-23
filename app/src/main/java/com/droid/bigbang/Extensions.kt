package com.droid.bigbang

import android.view.View
import com.droid.bigbang.models.Photo
import java.lang.StringBuilder

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun getPhotoMeta(photo: Photo): String {
    val meta = StringBuilder()
    return meta.toString()
}

