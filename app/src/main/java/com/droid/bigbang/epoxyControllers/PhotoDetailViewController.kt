@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package com.droid.bigbang.epoxyControllers

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.carousel
import com.droid.bigbang.PhotoDetailView_
import com.droid.bigbang.models.Photo

class PhotoDetailViewController : AsyncEpoxyController() {

    var scrollPosition = 0

    var photoList: List<Photo> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
         val photoDetail = photoList.map {
             PhotoDetailView_()
                 .id(it.url)
                 .photo(it)
         }

        carousel {
            id("photo_carousel")
            models(photoDetail)
            numViewsToShowOnScreen(1.2F)
            onBind { model, view, position ->
                view.scrollToPosition(scrollPosition)
            }
        }
    }
}