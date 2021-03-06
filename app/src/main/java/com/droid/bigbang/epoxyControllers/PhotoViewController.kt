@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package com.droid.bigbang.epoxyControllers

import com.airbnb.epoxy.AsyncEpoxyController
import com.droid.bigbang.PhotoDetailsActivity
import com.droid.bigbang.models.Photo
import com.droid.bigbang.photoView

class PhotoViewController : AsyncEpoxyController() {
    var photoList: List<Photo> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        photoList.forEach { currentPhoto ->
            photoView {
                id(currentPhoto.url)
                photo(currentPhoto)
                    .clickListener { model, parentView, clickedView, position ->
                        PhotoDetailsActivity.launchWith(
                            clickedView.context, position,
                            photoList.toMutableList() as ArrayList<Photo>
                        )
                    }
            }
        }
    }
}